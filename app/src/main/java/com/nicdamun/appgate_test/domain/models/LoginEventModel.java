package com.nicdamun.appgate_test.domain.models;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class LoginEventModel {

    private final String username;
    private final String password;
    private final Date timeStamp;

    public LoginEventModel(
        final String username,
        final String password,
        final Date timeStamp
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginEventModel user = (LoginEventModel) o;

        return username.equals(user.username) &&
            password.equals(user.password) &&
            timeStamp.compareTo(user.timeStamp) == 0;
    }



    @Override
    public int hashCode() {
        return Objects.hash(username, password, timeStamp);
    }



    @NonNull
    @Override
    public String toString() {
        return "User{" +
            "username=" + username +
            ", password=" + password +
            ", timestamp=" + timeStamp +
            "}";
    }
}
