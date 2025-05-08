package br.com.jota.room.server.respository;

import br.com.jota.room.server.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRespository extends JpaRepository<Room, UUID> {
    Optional<Room> findByRoomNumber(Integer roomNumber);
}
