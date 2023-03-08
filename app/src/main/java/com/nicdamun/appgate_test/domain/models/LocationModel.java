package com.nicdamun.appgate_test.domain.models;

public class LocationModel {

    private final double latitude;
    private final double longitude;

    public LocationModel(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
