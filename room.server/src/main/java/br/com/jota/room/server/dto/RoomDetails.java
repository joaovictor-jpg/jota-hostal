package br.com.jota.room.server.dto;

import br.com.jota.room.server.entity.Room;
import br.com.jota.room.server.entity.Status;

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
    public RoomDetails(Room room) {
        this(room.getIdRoom(), room.getAreaM2(), room.getRoomNumber(), room.getRentalValue(), room.getCondoFee(), room.getAcceptsAnimals(),
                room.getAddress(), room.getDescription(), room.getTelephone(), room.getStatus(), room.getImageUrl());
    }
}
