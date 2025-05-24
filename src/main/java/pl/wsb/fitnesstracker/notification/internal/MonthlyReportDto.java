package pl.wsb.fitnesstracker.notification.internal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.Set;

/**
 * Data Transfer Object (DTO) representing a summary of a user's training activity for a single month.
 *
 * <p>This record includes the total number of training sessions and a detailed list of those sessions.</p>
 *
 * @param trainingsCount total number of training sessions in the given month (must be >= 0)
 * @param trainings set of individual training session details
 *
 * @see TrainingDto
 */
record MonthlyReportDto(
        @NotNull @Min(0) int trainingsCount,
        @NotNull Set<ReportTrainingDto> trainings
        ) {
}
