package br.com.jota.Booking.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CreatedBooking(
        @NotNull(message = "Número do quarto é obrigatório")
        @Min(value = 1, message = "Número do quarto deve ser maior que zero")
        Integer roomNumber,
        @NotBlank(message = "Nome do hóspede é obrigatório")
        @Size(max = 100, message = "Nome do hóspede deve ter no máximo 100 caracteres")
        String nameGuest,
        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(
                regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-\\d{4}",
                message = "Telefone inválido. Exemplo: (11) 91234-5678"
        )
        String telephone,
        @Size(max = 500, message = "Mensagem deve ter no máximo 500 caracteres")
        String message,
        @NotNull(message = "Data de check-in é obrigatória")
        @FutureOrPresent(message = "Data de check-in não pode estar no passado")
        LocalDateTime checkIn,
        @NotNull(message = "Data de check-out é obrigatória")
        @Future(message = "Data de check-out deve ser no futuro")
        LocalDateTime checkOut,
        @NotBlank(message = "CPF do hóspede é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String guestCpf
) {
}
