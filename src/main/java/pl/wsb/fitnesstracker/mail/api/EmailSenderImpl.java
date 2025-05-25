package pl.wsb.fitnesstracker.mail.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;
    private final EmailMapper emailMapper;

    @Override
    public void send(EmailDto email) {
        log.info("Sending email with content: {}", email);

        var messsage = emailMapper.toSimpleMailMessage(email);

        javaMailSender.send(messsage);
    }
}
