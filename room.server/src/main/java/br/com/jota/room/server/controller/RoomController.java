package br.com.jota.room.server.controller;

import br.com.jota.room.server.dto.CreatedRoom;
import br.com.jota.room.server.dto.RoomDetails;
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
}
