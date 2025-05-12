package br.com.jota.Booking.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record RoomDetails(
        UUID idRoom,
        BigDecimal areaM2,
        Integer roomNumber,
        BigDecimal rentalValue,
        BigDecimal condoFee,
        Boolean acceptsAnimals,
        String address,
        String description,
        String telephone,
        Status status,
        String imageUrl
) {
}
