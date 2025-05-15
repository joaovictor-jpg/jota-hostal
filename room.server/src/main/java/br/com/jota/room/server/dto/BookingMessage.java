package br.com.jota.room.server.dto;

import java.util.UUID;

public record BookingMessage(
        UUID idBooking,
        Integer roomNumber
) {
}
