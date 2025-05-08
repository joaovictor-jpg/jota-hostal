package br.com.jota.room.server.service;

import br.com.jota.room.server.dto.CreatedRoom;
import br.com.jota.room.server.dto.RoomDetails;
import br.com.jota.room.server.entity.Room;
import br.com.jota.room.server.respository.RoomRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomRespository roomRespository;

    public RoomService(RoomRespository roomRespository) {
        this.roomRespository = roomRespository;
    }

    public List<RoomDetails> listRoom() {
        return roomRespository.findAll().stream().map(RoomDetails::new).toList();
    }

    public UUID saveRoom(CreatedRoom createdRoom) {
        Room room = new Room(createdRoom);
        roomRespository.save(room);
        return room.getIdRoom();
    }
}
