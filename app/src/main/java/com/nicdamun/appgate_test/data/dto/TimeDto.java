package com.nicdamun.appgate_test.data.dto;

public class TimeDto {

    private final String currentLocalTime;

    public TimeDto(final String currentLocalTime) {
        this.currentLocalTime = currentLocalTime;
    }

    public String getCurrentLocalTime() {
        return currentLocalTime;
    }
}
