package br.com.jota.email.dto;

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
