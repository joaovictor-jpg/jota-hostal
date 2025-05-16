package br.com.jota.room.server.amqp;

import br.com.jota.room.server.dto.BookingMessage;
import br.com.jota.room.server.service.RoomService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingListener {

    private final RoomService roomService;

    public BookingListener(RoomService roomService) {
        this.roomService = roomService;
    }

    @RabbitListener(queues = "ReservationRequested")
    public void readMessage(BookingMessage message) {
        roomService.updatedStatusRoom(message);
    }

    @RabbitListener(queues = "CancelRequested")
    public void cancelReservetion(BookingMessage message) {
        roomService.cancelReservation(message);
    }
}
