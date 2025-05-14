package pl.wsb.fitnesstracker.training.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrainingServiceImplUnitTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TrainingServiceImpl trainingService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void getTrainingById_shouldReturnTrainingById() {
//        // given
//        final User exampleUser = new User(
//                "fn", "ln", LocalDate.of(2000, 10, 10), "e@g.c"
//        );
//
//        final Training training = new Training(
//                exampleUser,
//                new Date("2024-01-19 08:00:00"),
//                new Date("2024-01-19 09:00:00"),
//                ActivityType.TENNIS,
//                213.7,
//                7.35
//        );
//
//        when()
//
//        // when
//    }

    @Test
    public void getAllTrainingsByUserId_shouldReturnUsersTrainings() {
        // given
        final Set<Training> exampleTrainings = Set.of(
                generateTraining(),
                generateTraining()
        );

        when(trainingRepository.findAllByUserId(anyLong())).thenReturn(exampleTrainings);

        // when
        final Set<Training> trainings = trainingService.getAllTrainingsByUserId(2L);

        // then
        assertEquals(trainings, exampleTrainings);

    }

    @Test
    public void getAllTrainings_shouldReturnAllTrainings() {
        // given
        final List<Training> exampleTrainings = List.of(
                generateTraining(),
                generateTraining()
        );

        when(trainingRepository.findAll()).thenReturn(exampleTrainings);

        // when
        final List<Training> trainings = trainingService.getAllTrainings();

        // then
        assertEquals(trainings, exampleTrainings);
    }

    @Test
    public void getAllTrainingsAfterDate_shouldReturnTrainings() {
        // given
        final Set<Training> exampleTrainings = Set.of(
                generateTraining(),
                generateTraining()
        );

        when(trainingRepository.findAllByEndTimeAfter(any(Date.class))).thenReturn(exampleTrainings);

        // when
        final Set<Training> trainings = trainingService.getAllTrainingsAfterDate(new Date());

        // then
        assertEquals(trainings, exampleTrainings);
    }

    @Test
    public void getAllTrainingsByActivityType_shouldReturnTrainings() {
        // given
        final Set<Training> exampleTrainings = Set.of(
                generateTraining(),
                generateTraining()
        );

        when(trainingRepository.findByActivityType(any(ActivityType.class))).thenReturn(exampleTrainings);

        // when
        final Set<Training> trainings = trainingService.getAllTrainingsByActivityType(ActivityType.TENNIS);

        // then
        assertEquals(trainings, exampleTrainings);
    }

    @Test
    public void createTraining_shouldReturnTraining() {
        // given
        final User user = generateUser();
        final Training t = generateTraining();

        final TrainingRequestDto dto = new TrainingRequestDto(
                1L, t.getStartTime(), t.getEndTime(), t.getActivityType(), t.getDistance(), t.getAverageSpeed()
        );

        when(trainingRepository.save(any(Training.class))).thenReturn(t);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // when
        final Training training = trainingService.createTraining(dto);

        // given
        assertEquals(training, t);
        verify(userRepository).findById(anyLong());
    }

    @Test
    public void updateTraining_shouldCorrectlyUpdateTraining() {
        // given
        final User user = generateUser();
        final Training t = generateTraining();

        final TrainingRequestDto dto = new TrainingRequestDto(
                1L, t.getStartTime(), t.getEndTime(), t.getActivityType(), t.getDistance(), t.getAverageSpeed()
        );

        when(trainingRepository.findById(anyLong())).thenReturn(Optional.of(t));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // when
        final Training training = trainingService.updateTraining(dto, 7L);

        // given
//        assertEquals(training, t);
        verify(userRepository).findById(anyLong());
        verify(trainingRepository).findById(anyLong());
    }


    private Training generateTraining() {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return new Training(
                    generateUser(),
                    sdf.parse("2024-01-19 08:00:00"),
                    sdf.parse("2024-01-19 09:00:00"),
                    ActivityType.TENNIS,
                    213.7,
                    7.35
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private User generateUser() {
        return new User(
                "fn", "ln", LocalDate.of(2000, 10, 10), "e@g.c"
        );
    }
}
