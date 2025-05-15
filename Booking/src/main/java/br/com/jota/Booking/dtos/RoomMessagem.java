package br.com.jota.Booking.dtos;

import java.util.UUID;

public record RoomMessagem(
        String message,
        UUID idBooking,
        Integer roomNumber
) {
}
