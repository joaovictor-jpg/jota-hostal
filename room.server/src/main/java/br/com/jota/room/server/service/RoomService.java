package br.com.jota.room.server.service;

import br.com.jota.room.server.dto.CreatedRoom;
import br.com.jota.room.server.respository.RoomRespository;
import jakarta.persistence.Transient;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRespository roomRespository;

    public RoomService(RoomRespository roomRespository) {
        this.roomRespository = roomRespository;
    }

    @Transient
    public void saveRoom(CreatedRoom createdRoom) {

    }
}
