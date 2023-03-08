package com.nicdamun.appgate_test.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.nicdamun.appgate_test.data.dataSource.AuthLocalDataSource;
import com.nicdamun.appgate_test.data.dataSource.LocationLocalDataSource;
import com.nicdamun.appgate_test.data.dataSource.TimeRemoteDataSource;
import com.nicdamun.appgate_test.data.repository.AuthRepository;
import com.nicdamun.appgate_test.data.repository.LocationRepository;
import com.nicdamun.appgate_test.data.repository.TimeRepository;
import com.nicdamun.appgate_test.domain.useCases.GetLoginInfoUseCase;
import com.nicdamun.appgate_test.domain.useCases.LoginUseCase;
import com.nicdamun.appgate_test.presentation.home.HomeViewModel;
import com.nicdamun.appgate_test.presentation.login.LoginViewModel;

public class AppContainer {

    private static final String AUTH_PREFERENCES_KEY = "AUTH_PREFERENCES";

    // Presentation
    public LoginViewModel loginViewModel;
    public HomeViewModel homeViewModel;

    public AppContainer(final Context context) {
        final FusedLocationProviderClient locationClient = LocationServices
            .getFusedLocationProviderClient(context);
        final SharedPreferences preferences = context.getSharedPreferences(
                AUTH_PREFERENCES_KEY,
                Context.MODE_PRIVATE
        );
        // DataSources
        final AuthLocalDataSource authLocalDataSource = new AuthLocalDataSource(preferences);
        final TimeRemoteDataSource timeRemoteDataSource = new TimeRemoteDataSource();
        final LocationLocalDataSource locationLocalDataSource = new LocationLocalDataSource(
            locationClient
        );

        // Repositories
        final AuthRepository authRepository = new AuthRepository(authLocalDataSource);
        final TimeRepository timeRepository = new TimeRepository(timeRemoteDataSource);
        final LocationRepository locationRepository = new LocationRepository(
            locationLocalDataSource
        );

        // Use Cases
        final LoginUseCase loginUseCase = new LoginUseCase(
            authRepository,
            timeRepository,
            locationRepository
        );
        final GetLoginInfoUseCase getLoginInfoUseCase = new GetLoginInfoUseCase(
            authRepository
        );

        // View Models
        loginViewModel = new LoginViewModel(loginUseCase);
        homeViewModel = new HomeViewModel(getLoginInfoUseCase);
    }
}
