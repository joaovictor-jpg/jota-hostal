package br.com.jota.Booking.Infraestrutura.dtos;


import br.com.jota.Booking.domain.entity.Booking;
import br.com.jota.Booking.domain.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookingDetails(
        UUID id,
        Integer roomNumber,
        String email,
        String nameGuest,
        BigDecimal totalPrice,
        String telephone,
        String message,
        BookingStatus status,
        LocalDateTime checkIn,
        LocalDateTime checkOut
) {
    public BookingDetails(Booking booking) {
        this(booking.getId(), booking.getRoomNumber(), booking.getEmail(), booking.getNameGuest(), booking.getTotalPrice(), booking.getTelephone(), booking.getMessage(), booking.getStatus(), booking.getCheckIn(),
                booking.getCheckOut());
    }
}
