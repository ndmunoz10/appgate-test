package com.nicdamun.appgate_test.data.dataSource;

import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.nicdamun.appgate_test.data.dto.LoginEventDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthLocalDataSource {

    private final SharedPreferences preferences;

    public AuthLocalDataSource(final SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public boolean loginUser(final LoginEventDto eventDto) {
        final String id = eventDto.getUsername() + "-" + eventDto.getPassword();
        final String existingEvent = preferences.getString(id, null);
        if (existingEvent == null) {
            return preferences.edit().putString(id, eventDto.getTimeStamp() + ",").commit();
        } else {
            return preferences.edit().putString(
                id,
                existingEvent + eventDto.getTimeStamp() + ","
            ).commit();
        }
    }

    @Nullable
    public List<LoginEventDto> getLoginInfo(final String username, final String password) {
        final String id = username + "-" + password;
        final String stringObject = preferences.getString(id, null);
        if (stringObject == null) return List.of();
        return Arrays.stream(stringObject.split(",")).map((dateTime ->
            new LoginEventDto(username, password, dateTime))
        ).collect(Collectors.toList());
    }
}
