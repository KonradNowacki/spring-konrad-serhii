package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.training.api.ActivityType;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.Set;

@Repository
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves all trainings associated with a specific user by their user ID.
     *
     * @param userId the ID of the user
     * @return a set of {@link Training} entities linked to the given user
     */
    Set<Training> findAllByUserId(Long userId);

    /**
     * Retrieves all trainings that ended after the specified date.
     *
     * @param endTimeAfter the cutoff {@link Date}; only trainings ending after this will be returned
     * @return a set of {@link Training} entities that finished after the given time
     */
    Set<Training> findAllByEndTimeAfter(Date endTimeAfter);

    /**
     * Retrieves all trainings filtered by the specified activity type.
     *
     * @param activityType the {@link ActivityType} to filter trainings
     * @return a set of {@link Training} entities matching the given activity type
     */
    Set<Training> findByActivityType(ActivityType activityType);

}
