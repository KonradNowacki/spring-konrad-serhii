package pl.wsb.fitnesstracker.notification.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
    public void generateAndSendReport() {

        final List<User> users = userRepository.findAll();

        final Date oneMonthAgo = Date.from(LocalDate.now().minusMonths(24)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());

        for (var user : users) {

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
}
