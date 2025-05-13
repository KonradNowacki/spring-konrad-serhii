package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTrainingById(Long trainingId);

    /**
     * Retrieves all training sessions for a specific user.
     *
     * @param userId the ID of the user whose trainings should be retrieved
     * @return a set of {@link Training} sessions associated with the given user ID
     */
    Set<Training> getAllTrainingsByUserId(Long userId);

    /**
     * Retrieves all training sessions that ended after the specified date.
     *
     * @param date the date after which training sessions should be included
     * @return a set of {@link Training} sessions that ended after the given date
     */
    Set<Training> getAllTrainingsAfterDate(Date date);

    /**
     * Retrieves all training sessions for a specific activity type.
     *
     * @param activityType the type of activity (e.g., RUNNING, CYCLING) to filter trainings
     * @return a set of {@link Training} sessions matching the specified activity type
     */
    Set<Training> getAllTrainingsByActivityType(ActivityType activityType);

    /**
     * Retrieves all training sessions stored in the system.
     *
     * @return a list of all {@link Training} sessions
     */
    List<Training> getAllTrainings();

}
