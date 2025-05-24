package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.ActivityType;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    // TODO KN Do i need this?
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

    @Override
    public Training createTraining(final TrainingRequestDto trainingDto) {

        final User user = userRepository.findById(trainingDto.userId()).orElseThrow(() -> new UserNotFoundException(trainingDto.userId()));

        final Training newTraining = new Training(
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );

        return trainingRepository.save(newTraining);
    }

    @Override
    public Training updateTraining(final TrainingRequestDto trainingDto, final Long trainingId) {
        final User user = userRepository.findById(trainingDto.userId()).orElseThrow(() -> new UserNotFoundException(trainingDto.userId()));
        final Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new TrainingNotFoundException(trainingId));

        if (trainingDto.startTime() != null) {
            training.setStartTime(trainingDto.startTime());
        }

        if (trainingDto.endTime() != null) {
            training.setEndTime(trainingDto.endTime());
        }

        if (trainingDto.activityType() != null) {
            training.setActivityType(trainingDto.activityType());
        }

        training.setUser(user);
        training.setDistance(trainingDto.distance());
        training.setAverageSpeed(trainingDto.averageSpeed());

        return trainingRepository.save(training);
    }
}
