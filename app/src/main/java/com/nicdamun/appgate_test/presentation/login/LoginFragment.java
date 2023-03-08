package com.nicdamun.appgate_test.presentation.login;

import static com.nicdamun.appgate_test.presentation.home.HomeFragment.PASSWORD_KEY;
import static com.nicdamun.appgate_test.presentation.home.HomeFragment.USERNAME_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.nicdamun.appgate_test.R;
import com.nicdamun.appgate_test.databinding.FragmentLoginBinding;
import com.nicdamun.appgate_test.di.AppContainer;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        final FragmentActivity activity = getActivity();
        if (activity != null && viewModel == null) {
            viewModel = new AppContainer(activity).loginViewModel;
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClickListeners();
        initObservers();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        viewModel = null;
    }

    @SuppressWarnings("ConstantConditions")
    private void initObservers() {
        viewModel.loginState.observe(getViewLifecycleOwner(), (final LoginViewModel.State state) -> {
            if (state == LoginViewModel.State.SUCCESS) {
                final Bundle bundle = new Bundle();
                bundle.putString(USERNAME_KEY, binding.etUsername.getEditText().getText().toString());
                bundle.putString(PASSWORD_KEY, binding.etPassword.getEditText().getText().toString());
                NavHostFragment.findNavController(this)
                    .navigate(
                        R.id.action_LoginFragment_to_HomeFragment,
                        bundle
                    );
            } else if (state == LoginViewModel.State.ERROR) {
                Toast.makeText(
                    getContext(),
                    getString(R.string.login_generic_error),
                    Toast.LENGTH_SHORT
                ).show();
            } else if (state == LoginViewModel.State.MISSING_CHARACTERS) {
                Toast.makeText(
                    getContext(),
                    getString(R.string.login_missing_characters_error),
                    Toast.LENGTH_SHORT
                ).show();
            }
            handleViews(state != LoginViewModel.State.LOADING);
        });
    }

    @SuppressWarnings("ConstantConditions")
    private void setClickListeners() {
        binding.btnEnter.setOnClickListener((v) ->
            viewModel.login(
                binding.etUsername.getEditText().getText().toString(),
                binding.etPassword.getEditText().getText().toString()
            )
        );
    }

    private void handleViews(final boolean shouldEnable) {
        binding.etUsername.setEnabled(shouldEnable);
        binding.etPassword.setEnabled(shouldEnable);
        binding.btnEnter.setEnabled(shouldEnable);
    }
}
