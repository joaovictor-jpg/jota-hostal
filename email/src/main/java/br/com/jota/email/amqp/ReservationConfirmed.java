package br.com.jota.email.amqp;

import br.com.jota.email.dto.SendEmail;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationConfirmed {
    @RabbitListener(queues = "ReservationConfirmed")
    public void reservationConfirmed(SendEmail email) {
        System.out.println(email);
    }
}
