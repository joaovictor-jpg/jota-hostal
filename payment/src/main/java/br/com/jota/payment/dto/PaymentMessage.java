package br.com.jota.payment.dto;

import java.util.UUID;

public record PaymentMessage(
        String messagem,
        UUID idBooking
) {
}
