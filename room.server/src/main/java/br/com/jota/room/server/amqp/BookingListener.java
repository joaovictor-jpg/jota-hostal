package br.com.jota.room.server.amqp;

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
    public void readMessage(Message message) {
        byte[] body = message.getBody();
        String content = new String(body, StandardCharsets.UTF_8);

        roomService.updatedStatusRoom(Integer.parseInt(content));
    }
}
