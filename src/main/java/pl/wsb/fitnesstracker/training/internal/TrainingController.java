package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Retrieves all trainings associated with a specific user by their ID.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a set of {@link TrainingResponseDto} representing the user's trainings
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public Set<TrainingResponseDto> findAll(@PathVariable Long userId) {
        return trainingService.getAllTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves all trainings in the system.
     *
     * @return a set of {@link TrainingResponseDto} representing all trainings
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Set<TrainingResponseDto> findAll() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves all trainings that were finished after the specified date.
     *
     * @param afterDate the {@link Date} after which finished trainings should be returned
     * @return a set of {@link TrainingResponseDto} representing trainings after the given date
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/finished/{afterDate}")
    public Set<TrainingResponseDto> getAllTrainingsAfterTime(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterDate) {
        System.out.println("DEBUG " + afterDate);
        return trainingService.getAllTrainingsAfterDate(afterDate)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves all trainings filtered by the specified activity type.
     *
     * @param activityType the {@link ActivityType} to filter trainings by
     * @return a set of {@link TrainingResponseDto} representing the filtered trainings
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/activityType")
    public Set<TrainingResponseDto> getAllTrainingsAfterTime(@RequestParam("activityType") ActivityType activityType) {
        return trainingService.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * Creates a new training based on the provided data.
     *
     * @param trainingDto the {@link TrainingRequestDto} containing training details
     * @return the created {@link TrainingResponseDto}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public TrainingResponseDto createTraining(@RequestBody TrainingRequestDto trainingDto) {
        final Training training = trainingService.createTraining(trainingDto);
        return trainingMapper.toDto(training);
    }

    /**
     * Updates an existing training with the given ID using the provided data.
     *
     * @param trainingDto the {@link TrainingRequestDto} containing updated training data
     * @param trainingId the ID of the training to update
     * @return the updated {@link TrainingResponseDto}
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{trainingId}")
    public TrainingResponseDto updateTraining(
            @RequestBody TrainingRequestDto trainingDto,
            @PathVariable Long trainingId) {
        final Training training = trainingService.updateTraining(trainingDto, trainingId);
        return trainingMapper.toDto(training);
    }

}
