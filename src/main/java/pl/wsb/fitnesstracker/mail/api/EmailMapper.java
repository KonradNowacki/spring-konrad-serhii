package pl.wsb.fitnesstracker.mail.api;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
class EmailMapper {

    public SimpleMailMessage toSimpleMailMessage(EmailDto emailDto) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("konrad.serhii@test.pl");
        message.setTo(emailDto.toAddress());
        message.setSubject(emailDto.subject());
        message.setText(emailDto.content());
        return message;
    }

}
