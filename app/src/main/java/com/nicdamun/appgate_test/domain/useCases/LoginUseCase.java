package com.nicdamun.appgate_test.domain.useCases;

import androidx.annotation.Nullable;

import com.nicdamun.appgate_test.data.dto.LoginEventDto;
import com.nicdamun.appgate_test.data.repository.AuthRepository;
import com.nicdamun.appgate_test.data.repository.LocationRepository;
import com.nicdamun.appgate_test.data.repository.TimeRepository;

import java.util.concurrent.CompletableFuture;

public class LoginUseCase {

    private final AuthRepository authRepository;
    private final TimeRepository timeRepository;
    private final LocationRepository locationRepository;

    public LoginUseCase(
        final AuthRepository authRepository,
        final TimeRepository timeRepository,
        final LocationRepository locationRepository
    ) {
        this.authRepository = authRepository;
        this.timeRepository = timeRepository;
        this.locationRepository = locationRepository;
    }

    public CompletableFuture<Boolean> invoke(final String username, final String password) {
        return locationRepository.getCurrentLocation().thenCompose(location -> timeRepository.getCurrentTime(location)
            .thenApply((timeModel -> loginEvent(username, password, timeModel.getCurrentLocalTime())))
            .exceptionally((ex) -> {
                loginEvent(username, password, null);
                return false;
            })
        ).exceptionally((ex) -> loginEvent(username, password, null));
    }

    private boolean loginEvent(
        final String username,
        final String password,
        @Nullable final String localTime
    ) {
        final LoginEventDto loginEventDto = new LoginEventDto(
            username,
            password,
            localTime
        );
        return authRepository.loginUser(loginEventDto);
    }
}
