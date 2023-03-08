package com.nicdamun.appgate_test.domain.models;

public class TimeModel {

    private final String currentLocalTime;

    public TimeModel(final String currentLocalTime) {
        this.currentLocalTime = currentLocalTime;
    }

    public String getCurrentLocalTime() {
        return currentLocalTime;
    }
}
