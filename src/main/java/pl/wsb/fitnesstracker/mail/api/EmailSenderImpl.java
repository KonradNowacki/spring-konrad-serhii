package pl.wsb.fitnesstracker.mail.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailSenderImpl implements EmailSender {


    @Override
    public void send(EmailDto email) {
        log.info("Sending email with content: {}", email);
    }
}
