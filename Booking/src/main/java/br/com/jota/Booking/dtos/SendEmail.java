package br.com.jota.Booking.dtos;

import java.time.LocalDateTime;

public record SendEmail(
        String email,
        Integer roomNumber,
        String nameGuest,
        String mensagem,
        LocalDateTime checkIn,
        LocalDateTime checkOut
) {
}
