package br.com.jota.Booking.amqp;

import br.com.jota.Booking.service.BookingService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoomListener {
    private final BookingService bookingService;

    public RoomListener(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RabbitListener(queues = "RoomAvailable")
    public void readMessage(Message message) {
        System.out.println("Payload: " + new String(message.getBody()));
    }
}
