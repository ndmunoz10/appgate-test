package com.nicdamun.appgate_test.data.repository;

import com.nicdamun.appgate_test.data.dataSource.TimeRemoteDataSource;
import com.nicdamun.appgate_test.domain.models.LocationModel;
import com.nicdamun.appgate_test.domain.models.TimeModel;

import java.util.concurrent.CompletableFuture;

public class TimeRepository {

    private final TimeRemoteDataSource timeRemoteDataSource;

    public TimeRepository(final TimeRemoteDataSource timeRemoteDataSource) {
        this.timeRemoteDataSource = timeRemoteDataSource;
    }

    public CompletableFuture<TimeModel> getCurrentTime(
        final LocationModel locationModel
    ) {
        return timeRemoteDataSource.getCurrentTime(
            locationModel.getLatitude(),
            locationModel.getLongitude()
        ).thenApply((timeDto -> new TimeModel(timeDto.getCurrentLocalTime())));
    }
}
