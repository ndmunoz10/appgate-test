package com.nicdamun.appgate_test.data.dataSource;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Priority;

import java.util.concurrent.CompletableFuture;

public class LocationLocalDataSource {

    private final FusedLocationProviderClient client;

    public LocationLocalDataSource(FusedLocationProviderClient client) {
        this.client = client;
    }

    public CompletableFuture<Location> getCurrentLocation() throws SecurityException {
        final CompletableFuture<Location> locationFuture = new CompletableFuture<>();
        client.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        ).addOnSuccessListener(locationFuture::complete);
        return locationFuture.toCompletableFuture();
    }
}
