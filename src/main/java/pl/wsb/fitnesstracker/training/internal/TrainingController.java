package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public Set<TrainingDto> findAll(@PathVariable Long userId) {
        return trainingService.getAllTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Set<TrainingDto> findAll() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/finished/{afterDate}")
    public Set<TrainingDto> getAllTrainingsAfterTime(@PathVariable Date afterDate) {
        return trainingService.getAllTrainingsAfterDate(afterDate)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/activityType")
    public Set<TrainingDto> getAllTrainingsAfterTime(@RequestParam("activityType") ActivityType activityType) {
        return trainingService.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

}
