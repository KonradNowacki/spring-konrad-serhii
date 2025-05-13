package pl.wsb.fitnesstracker.training.internal;

import org.junit.jupiter.api.Assertions;
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
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
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
    public void getAllTrainingsByUserId_shouldReturnAllTrainings() {
        // given
        final Set<Training> exampleTrainings = Set.of(
                generateTraining("t1"),
                generateTraining("t2")
        );

        when(trainingRepository.findAllByUserId(anyLong())).thenReturn(exampleTrainings);

        // when
        final Set<Training> trainings = trainingService.getAllTrainingsByUserId(2L);

        // then
        Assertions.assertEquals(trainings, exampleTrainings);

    }

    private Training generateTraining(String name) {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        final User exampleUser = new User(
                "fn", "ln", LocalDate.of(2000, 10, 10), "e@g.c"
        );

        try {
            return new Training(
                    exampleUser,
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
}
