package pl.wsb.fitnesstracker.notification.internal;

import pl.wsb.fitnesstracker.mail.api.EmailSender;

/**
 * Implementation of the {@link ReportService} interface responsible for generating
 * and sending monthly training reports to users.
 *
 * <p>This service gathers user training data, transforms it into a report-friendly format,
 * and dispatches the report via email using the {@link EmailSender}.</p>
 *
 */
interface ReportService {

    /**
     * Generates and sends a training report for a specific user.
     *
     * <p>The report includes the list of training sessions from the last month.
     * It is formatted and sent to the user's registered email address.</p>
     *
     * @param userId the ID of the user for whom the report is generated
     * @throws UserNotFoundException if no user is found with the provided ID
     * @throws EmailSendException if an error occurs while sending the report email
     */
    void generateAndSendReport(Long userId);

}
