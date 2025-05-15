package br.com.jota.room.server.amqp;

import br.com.jota.room.server.dto.BookingMessage;
import br.com.jota.room.server.service.RoomService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

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
}
