package br.com.jota.Booking.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentCreation(
        UUID idBooking,
        String email,
        String nameGuest,
        BigDecimal totalPrice,
        String telephone,
        String guestCpf
) {
}
