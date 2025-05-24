package pl.wsb.fitnesstracker.notification.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler component responsible for triggering the monthly training report generation.
 *
 * <p>This class uses Spring's {@link Scheduled} annotation to periodically execute
 * the report generation job for users.</p>
 *
 * <p>Currently, it simulates the behavior for a single user with a fixed ID.</p>
 *
 * @see ReportService
 */
@Component
@RequiredArgsConstructor
class ReportScheduler {

    private final ReportService reportService;

    /**
     * Scheduler component responsible for triggering the monthly training report generation.
     *
     * <p>This class uses Spring's {@link Scheduled} annotation to periodically execute
     * the report generation job for users.</p>
     *
     * <p>Currently, it simulates the behavior for a single user with a fixed ID.</p>
     *
     * @see ReportService
     */
//    @Scheduled(cron = "0 0 8 1 * *")
    @Scheduled(cron = "0/10 * * * * *")             // Job run every 10s for testing purposes
    public void runMonthlyReport() {

        // Simulating id of the logged-in user
        final Long userId = 1L;

        reportService.generateAndSendReport(userId);
    }

}
