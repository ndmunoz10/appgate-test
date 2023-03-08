package com.nicdamun.appgate_test.presentation.login;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nicdamun.appgate_test.domain.useCases.LoginUseCase;

public class LoginViewModel extends ViewModel {

    private final LoginUseCase loginUseCase;

    private final MutableLiveData<State> _loginState = new MutableLiveData<>(State.IDLE);
    public final LiveData<State> loginState = _loginState;

    public LoginViewModel(final LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    void login(final String username, final String password) {
        _loginState.postValue(State.LOADING);
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            _loginState.postValue(State.ERROR);
            return;
        }
        if (password.length() < 8) {
            _loginState.postValue(State.MISSING_CHARACTERS);
            return;
        }
        loginUseCase.invoke(username, password)
            .thenAccept((result) -> {
                if (result) {
                    _loginState.postValue(State.SUCCESS);
                } else {
                    _loginState.postValue(State.ERROR);
                }
            }).handle((res, exception) -> false);
    }

    public enum State {
        LOADING,
        IDLE,
        SUCCESS,
        ERROR,
        MISSING_CHARACTERS
    }
}
