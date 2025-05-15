package br.com.jota.Booking.dtos;

import java.util.UUID;

public record BookingMessagem(
        UUID idBooking,
        Integer roomNumber
) {
}
