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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public Set<TrainingResponseDto> findAll(@PathVariable Long userId) {
        return trainingService.getAllTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Set<TrainingResponseDto> findAll() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/activityType")
    public Set<TrainingResponseDto> getAllTrainingsAfterTime(@RequestParam("activityType") ActivityType activityType) {
        return trainingService.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public TrainingResponseDto createTraining(@RequestBody TrainingRequestDto trainingDto) {
        final Training training = trainingService.createTraining(trainingDto);
        return trainingMapper.toDto(training);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{trainingId}")
    public TrainingResponseDto updateTraining(
            @RequestBody TrainingRequestDto trainingDto,
            @PathVariable Long trainingId) {
        final Training training = trainingService.updateTraining(trainingDto, trainingId);
        return trainingMapper.toDto(training);
    }

}
