package com.nicdamun.appgate_test.data.dto;

public class LoginEventDto {

    private final String username;
    private final String password;
    private final String timeStamp;

    public LoginEventDto(
        final String username,
        final String password,
        final String timeStamp
    ) {
        this.username = username;
        this.password = password;
        this.timeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
