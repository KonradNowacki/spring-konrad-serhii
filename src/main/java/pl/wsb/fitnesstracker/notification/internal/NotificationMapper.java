package pl.wsb.fitnesstracker.notification.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;

/**
 * Mapper component responsible for transforming domain entities into DTOs
 * used in notifications and reports.
 *
 * <p>Specifically maps {@link Training} entities to {@link ReportTrainingDto} objects.</p>
 *
 * @see ReportTrainingDto
 * @see Training
 */
@Component
class NotificationMapper {

    /**
     * Converts a {@link Training} entity into a {@link ReportTrainingDto}.
     *
     * @param training the training entity to convert
     * @return a corresponding {@link ReportTrainingDto} containing report-friendly training data
     */
    public ReportTrainingDto toReportTrainingDto(Training training) {
        return new ReportTrainingDto(
                training.getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

}
