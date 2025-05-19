package br.com.jota.Booking.amqp;

import br.com.jota.Booking.dtos.PaymentMessage;
import br.com.jota.Booking.service.BookingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {
    private final BookingService bookingService;

    public PaymentListener(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RabbitListener(queues = "CancelPayment")
    public void cancelPayment(PaymentMessage paymentMessage) {
        bookingService.deleteBooking(paymentMessage.idBooking());
    }
}
