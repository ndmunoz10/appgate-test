package com.nicdamun.appgate_test.presentation.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.nicdamun.appgate_test.databinding.FragmentHomeBinding;
import com.nicdamun.appgate_test.di.AppContainer;
import com.nicdamun.appgate_test.presentation.adapter.LoginEventsAdapter;

public class HomeFragment extends Fragment {

    public static final String USERNAME_KEY = "USERNAME_KEY";
    public static final String PASSWORD_KEY = "PASSWORD_KEY";

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        final FragmentActivity activity = getActivity();
        if (activity != null && viewModel == null) {
            viewModel = new AppContainer(activity).homeViewModel;
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void initViews() {
        final LoginEventsAdapter adapter = new LoginEventsAdapter();
        binding.rvLoginEvents.setAdapter(adapter);
        if (getArguments() != null) {
            final String username = getArguments().getString(USERNAME_KEY);
            final String password = getArguments().getString(PASSWORD_KEY);
            adapter.submitList(viewModel.getLoginInfoEvents(username, password));
        }
    }
}
