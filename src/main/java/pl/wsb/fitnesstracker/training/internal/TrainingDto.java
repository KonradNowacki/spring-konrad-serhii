package pl.wsb.fitnesstracker.training.internal;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

record TrainingDto(
        @Nullable Long id,
        UserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
}
