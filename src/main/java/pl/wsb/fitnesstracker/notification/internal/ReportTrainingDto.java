package pl.wsb.fitnesstracker.notification.internal;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.training.api.ActivityType;

import java.util.Date;

/**
 * Data Transfer Object (DTO) representing a single training session
 * used in the context of generating monthly reports.
 *
 * <p>This DTO includes basic metadata about the training such as start and end time,
 * activity type, distance covered, and average speed.</p>
 *
 * @param id optional identifier of the training (can be {@code null})
 * @param startTime timestamp indicating when the training session started
 * @param endTime timestamp indicating when the training session ended
 * @param activityType type of physical activity performed during the session
 * @param distance distance covered in the session (in kilometers or other unit)
 * @param averageSpeed average speed during the session (in km/h or other unit)
 *
 * @see ActivityType
 */
record ReportTrainingDto(
        @Nullable Long id,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
}
