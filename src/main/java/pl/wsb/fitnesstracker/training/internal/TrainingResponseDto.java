package pl.wsb.fitnesstracker.training.internal;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.training.api.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

/**
 * Data Transfer Object used for sending training session details in API responses.
 *
 * @param id            the unique identifier of the training session (nullable)
 * @param user          the {@link UserDto} representing the user who performed the training
 * @param startTime     the start time of the training session
 * @param endTime       the end time of the training session
 * @param activityType  the type of physical activity (e.g., RUNNING, SWIMMING)
 * @param distance      the distance covered during the training, in kilometers
 * @param averageSpeed  the average speed during the training, in kilometers per hour
 */
record TrainingResponseDto(
        @Nullable Long id,
        UserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
}
