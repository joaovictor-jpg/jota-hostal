package br.com.jota.email.amqp;

import br.com.jota.email.dto.SendEmail;
import br.com.jota.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationConfirmed {

    private final EmailService emailService;

    public ReservationConfirmed(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "ReservationConfirmed")
    public void reservationConfirmed(SendEmail email) {
        emailService.save(email);
    }
}
