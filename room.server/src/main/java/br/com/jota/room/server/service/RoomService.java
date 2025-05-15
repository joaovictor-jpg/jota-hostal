package br.com.jota.room.server.service;

import br.com.jota.room.server.dto.CreatedRoom;
import br.com.jota.room.server.dto.RoomDetails;
import br.com.jota.room.server.dto.UpdateRoom;
import br.com.jota.room.server.entity.Room;
import br.com.jota.room.server.respository.RoomRespository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static br.com.jota.room.server.entity.Status.RESERVED;

@Service
public class RoomService {
    private final RoomRespository roomRespository;
    public RoomService(RoomRespository roomRespository, RabbitTemplate rabbitTemplate) {
        this.roomRespository = roomRespository;
        this.rabbitTemplate = rabbitTemplate;
    }
    public final RabbitTemplate rabbitTemplate;

    public List<RoomDetails> listRoom() {
        return roomRespository.findAll().stream().map(RoomDetails::new).toList();
    }

    public UUID saveRoom(CreatedRoom createdRoom) {
        Room room = new Room(createdRoom);
        roomRespository.save(room);
        return room.getIdRoom();
    }

    public Room findBydRoomNumber(Integer roomNumber) {
        return roomRespository.findByRoomNumber(roomNumber).orElseThrow(() -> new EntityNotFoundException("Room not found"));
    }

    public RoomDetails updateRoomByRoomNumber(Integer roomNumber, UpdateRoom updateRoom) {
        Room room = roomRespository.findByRoomNumber(roomNumber).orElseThrow(() -> new EntityNotFoundException("Room not found"));
        room = updateRoom(updateRoom, room);

        roomRespository.save(room);

        return new RoomDetails(room);
    }

    public void emitirRoomAvailable() {
        Message message = new Message("successfully booked room".getBytes());
        rabbitTemplate.send("RoomAvailable", message);
    }

    public void deleteRoom(Integer roomNumber) {
        Room room = roomRespository.findByRoomNumber(roomNumber).orElseThrow(() -> new EntityNotFoundException("Room not found"));

        roomRespository.delete(room);
    }

    public void updatedStatusRoom(Integer roomNumber) {
        Room room = roomRespository.findByRoomNumber(roomNumber).orElseThrow(() -> new EntityNotFoundException("Room not found"));
        room.setStatus(RESERVED);
        emitirRoomAvailable();
        roomRespository.save(room);
    }

    private Room updateRoom(UpdateRoom updateRoom, Room room) {
        updateIfNotNull(updateRoom::areaM2, room::setAreaM2);
        updateIfNotNull(updateRoom::roomNumber, room::setRoomNumber);
        updateIfNotNull(updateRoom::rentalValue, room::setRentalValue);
        updateIfNotNull(updateRoom::condoFee, room::setCondoFee);
        updateIfNotNull(updateRoom::acceptsAnimals, room::setAcceptsAnimals);
        updateIfNotNull(updateRoom::address, room::setAddress);
        updateIfNotNull(updateRoom::description, room::setDescription);
        updateIfNotNull(updateRoom::telephone, room::setTelephone);

        return room;
    }

    private <T> void updateIfNotNull(Supplier<T> getter, Consumer<T> setter) {
        Optional.ofNullable(getter.get()).ifPresent(setter);
    }
}
