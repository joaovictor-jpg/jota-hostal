package br.com.jota.room.server.dto;

import java.util.UUID;

public record RoomMessagem(
        String message,
        UUID idBooking,
        Integer roomNumber
) {
    public RoomMessagem(String message, UUID idBooking, Integer roomNumber) {
        this.message = message;
        this.idBooking = idBooking;
        this.roomNumber = roomNumber;
    }
}
