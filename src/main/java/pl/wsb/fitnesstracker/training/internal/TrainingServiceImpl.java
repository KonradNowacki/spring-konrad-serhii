package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.*;

@Service
@RequiredArgsConstructor
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<User> getTrainingById(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public Set<Training> getAllTrainingsByUserId(Long userId) {
        return trainingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public Set<Training> getAllTrainingsAfterDate(Date date) {
        return trainingRepository.findAllByEndTimeAfter(date);
    }

    @Override
    public Set<Training> getAllTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }
}
