package br.com.jota.payment.amqp;

import br.com.jota.payment.dto.PaymentCreation;
import br.com.jota.payment.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingListener {
    private final PaymentService paymentService;

    public BookingListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RabbitListener(queues = "ReservationCreated")
    public void reservationCreated(PaymentCreation paymentCreation) {
        paymentService.createdPayment(paymentCreation);
    }
}
