package br.com.jota.room.server.controller;

import br.com.jota.room.server.dto.CreatedRoom;
import br.com.jota.room.server.dto.RoomDetails;
import br.com.jota.room.server.dto.UpdateRoom;
import br.com.jota.room.server.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDetails>> listRoom() {
        return ResponseEntity.ok().body(roomService.listRoom());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CreatedRoom createdRoom, UriComponentsBuilder uriComponentsBuilder) {
        UUID id = roomService.saveRoom(createdRoom);
        var url = uriComponentsBuilder.path("/{idroom}").buildAndExpand(id).toUri();
        return ResponseEntity.created(url).build();
    }

    @PutMapping("/{roomNumber}")
    public ResponseEntity<RoomDetails> update(@PathVariable("roomNumber") Integer roomNumber, @RequestBody @Valid UpdateRoom updateRoom) {
        var room = roomService.updateRoomByRoomNumber(roomNumber, updateRoom);
        return ResponseEntity.ok().body(room);
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<RoomDetails> findByNumberRoom(@PathVariable("roomNumber") Integer roomNumber) {
        var room = roomService.findBydRoomNumber(roomNumber);
        return ResponseEntity.ok().body(new RoomDetails(room));
    }

    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> delete(@PathVariable("roomNumber") Integer roomNumber) {
        roomService.deleteRoom(roomNumber);

        return ResponseEntity.noContent().build();
    }
}
