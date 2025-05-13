package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.Set;

@Repository
interface TrainingRepository extends JpaRepository<Training, Long> {

    Set<Training> findAllByUserId(Long userId);
    Set<Training> findAllByEndTimeAfter(Date endTimeAfter);
    Set<Training> findByActivityType(ActivityType activityType);

}
