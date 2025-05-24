package pl.wsb.fitnesstracker.notification.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
class ReportServiceImpl implements ReportService {

    private final EmailSender emailSender;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public void generateAndSendReport(Long userId) {

        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        final Date oneMonthAgo = Date.from(LocalDate.now().minusMonths(1)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());

        final Set<ReportTrainingDto> trainingsByUserId = user.getTrainings()
                .stream()
                .filter(t -> t.getStartTime().after(oneMonthAgo))
                .map(notificationMapper::toReportTrainingDto)
                .collect(Collectors.toSet());

        final MonthlyReportDto monthlyReportDto = new MonthlyReportDto(
                trainingsByUserId.size(),
                trainingsByUserId
        );

        final EmailDto emailDto = new EmailDto(
                user.getEmail(),
                "Monthly Trainings Summary!",
                monthlyReportDto.toString()
        );

        emailSender.send(emailDto);
    }
}
