package com.nicdamun.appgate_test.data.repository;

import com.nicdamun.appgate_test.data.dataSource.AuthLocalDataSource;
import com.nicdamun.appgate_test.data.dto.LoginEventDto;

import java.util.List;

public class AuthRepository {

    private final AuthLocalDataSource authLocalDataSource;

    public AuthRepository(AuthLocalDataSource authLocalDataSource) {
        this.authLocalDataSource = authLocalDataSource;
    }

    public boolean loginUser(final LoginEventDto loginEventDto) {
        return authLocalDataSource.loginUser(loginEventDto);
    }

    public List<LoginEventDto> getLoginEvents(final String username, final String password) {
        return authLocalDataSource.getLoginInfo(username, password);
    }
}
