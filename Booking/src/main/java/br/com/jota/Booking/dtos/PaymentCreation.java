package br.com.jota.Booking.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentCreation(
        UUID id,
        String email,
        String nameGuest,
        BigDecimal totalPrice,
        String telephone,
        String guestCpf
) {
}
