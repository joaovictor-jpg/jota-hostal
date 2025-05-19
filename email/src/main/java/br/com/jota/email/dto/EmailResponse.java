package br.com.jota.email.dto;

import br.com.jota.email.entity.Email;

import java.time.LocalDateTime;

public record EmailResponse(
        String email,
        Integer roomNumber,
        String nameGuest,
        String mensagem,
        LocalDateTime checkIn,
        LocalDateTime checkOut
) {
    public EmailResponse(Email email) {
        this(email.getEmail(), email.getRoomNumber(), email.getName(), email.getMessage(), email.getCheckIn(), email.getCheckOut());
    }
}
