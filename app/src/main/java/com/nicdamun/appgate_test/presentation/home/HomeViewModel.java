package com.nicdamun.appgate_test.presentation.home;

import androidx.lifecycle.ViewModel;

import com.nicdamun.appgate_test.domain.models.LoginEventModel;
import com.nicdamun.appgate_test.domain.useCases.GetLoginInfoUseCase;

import java.util.List;

public class HomeViewModel extends ViewModel {

    public final GetLoginInfoUseCase loginInfoUseCase;

    public HomeViewModel(final GetLoginInfoUseCase loginInfoUseCase) {
        this.loginInfoUseCase = loginInfoUseCase;
    }

    public List<LoginEventModel> getLoginInfoEvents(final String username, final String password) {
        return loginInfoUseCase.invoke(username, password);
    }
}
