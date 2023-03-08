package com.nicdamun.appgate_test.data.repository;

import com.nicdamun.appgate_test.data.dataSource.LocationLocalDataSource;
import com.nicdamun.appgate_test.domain.models.LocationModel;

import java.util.concurrent.CompletableFuture;

public class LocationRepository {

    private final LocationLocalDataSource locationLocalDataSource;

    public LocationRepository(LocationLocalDataSource locationLocalDataSource) {
        this.locationLocalDataSource = locationLocalDataSource;
    }

    public CompletableFuture<LocationModel> getCurrentLocation() {
        return locationLocalDataSource.getCurrentLocation().thenApply((location ->
            new LocationModel(location.getLatitude(), location.getLongitude()))
        );
    }
}
