package br.com.jota.room.server.service;

import br.com.jota.room.server.dto.RoomDetails;
import br.com.jota.room.server.respository.RoomRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRespository roomRespository;

    public RoomService(RoomRespository roomRespository) {
        this.roomRespository = roomRespository;
    }

    public List<RoomDetails> listRoom() {
        return roomRespository.findAll().stream().map(RoomDetails::new).toList();
    }
}
