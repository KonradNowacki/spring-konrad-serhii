package pl.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

/**
 * Data Transfer Object representing a training session.
 * This DTO is used for transferring training data between layers (e.g., API and service).
 *
 * @param id           the unique identifier of the training (nullable for new entries)
 * @param user         the user associated with the training
 * @param startTime    the start time of the training session
 * @param endTime      the end time of the training session
 * @param activityType the type of activity performed during the training
 * @param distance     the total distance covered in kilometers
 * @param averageSpeed the average speed in kilometers per hour
 */
public record TrainingDto(
        @Nullable Long id,
        UserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
}
