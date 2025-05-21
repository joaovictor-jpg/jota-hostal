package br.com.jota.email.dto;

public record CreatedEmail(
        String email,

        Integer roomNumber,

        String nameGuest,

        String title,

        String text
) {
}
