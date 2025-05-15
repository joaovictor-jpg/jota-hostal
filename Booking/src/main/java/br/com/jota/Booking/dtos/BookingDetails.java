package br.com.jota.Booking.dtos;

import br.com.jota.Booking.entity.Booking;
import br.com.jota.Booking.entity.BookingStatus;

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
