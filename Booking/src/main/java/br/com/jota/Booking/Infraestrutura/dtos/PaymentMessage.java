package br.com.jota.Booking.Infraestrutura.dtos;

import java.util.UUID;

public record PaymentMessage(
        String messagem,
        UUID idBooking
) {
}
