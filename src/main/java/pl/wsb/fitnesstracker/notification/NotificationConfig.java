package pl.wsb.fitnesstracker.notification;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Configuration class that enables scheduling support in the Spring application context.
 *
 * <p>This class activates Spring's task scheduling infrastructure using the
 * {@link EnableScheduling} annotation, allowing the use of {@link org.springframework.scheduling.annotation.Scheduled}
 * methods throughout the application.</p>
 *
 * @see org.springframework.scheduling.annotation.Scheduled
 * @see org.springframework.scheduling.annotation.EnableScheduling
 */
@EnableScheduling
@Configuration
public class NotificationConfig {
}
