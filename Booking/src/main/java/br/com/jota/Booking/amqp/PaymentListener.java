package br.com.jota.Booking.amqp;

import br.com.jota.Booking.application.gateways.ConfirmPayment;
import br.com.jota.Booking.application.gateways.DeleteBooking;
import br.com.jota.Booking.dtos.PaymentMessage;
import br.com.jota.Booking.service.BookingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {
    private final BookingService bookingService;
    private final DeleteBooking deleteBooking;
    private final ConfirmPayment confirmPayment;

    public PaymentListener(BookingService bookingService, DeleteBooking deleteBooking, ConfirmPayment confirmPayment) {
        this.bookingService = bookingService;
        this.deleteBooking = deleteBooking;
        this.confirmPayment = confirmPayment;
    }

    @RabbitListener(queues = "CancelPayment")
    public void cancelPayment(PaymentMessage paymentMessage) {
        deleteBooking.execute(paymentMessage.idBooking());
    }

    @RabbitListener(queues = "PaymentApproved")
    public void paymentApproved(PaymentMessage paymentMessage) {
        confirmPayment.execute(paymentMessage.idBooking());
    }
}
