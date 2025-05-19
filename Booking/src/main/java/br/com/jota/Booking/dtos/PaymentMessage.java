package br.com.jota.Booking.dtos;

import java.util.UUID;

public record PaymentMessage(
        String messagem,
        UUID idBooking
) {
}
