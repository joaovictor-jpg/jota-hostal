package br.com.jota.Booking.amqp;

import br.com.jota.Booking.dtos.PaymentMessage;
import br.com.jota.Booking.dtos.RoomMessagem;
import br.com.jota.Booking.service.BookingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoomListener {
    private final BookingService bookingService;

    public RoomListener(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RabbitListener(queues = "RoomAvailable")
    public void readMessage(RoomMessagem messagem) {
        bookingService.updateStatusBooking(messagem);
    }
}
