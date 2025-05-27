package br.com.jota.Booking.Infraestrutura.gateways;

import br.com.jota.Booking.Infraestrutura.http.RoomClient;
import br.com.jota.Booking.application.gateways.VerifyRoomAvailable;
import br.com.jota.Booking.domain.entity.Booking;
import br.com.jota.Booking.dtos.RoomDetails;
import br.com.jota.Booking.dtos.Status;
import br.com.jota.Booking.exception.BusinessRuleException;

import java.math.BigDecimal;

public class VerifyRoomAvailableCase implements VerifyRoomAvailable {
    private final RoomClient roomClient;

    public VerifyRoomAvailableCase(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    @Override
    public void execute(Booking booking) {
        RoomDetails room = roomClient.getRoom(booking.getRoomNumber());
        if (!room.status().equals(Status.ACTIVE)) {
            throw new BusinessRuleException("Quarto em Ocupado");
        }

        BigDecimal valueTotalForRoom = room.rentalValue().add(room.condoFee());

        booking.setTotalPrice(valueTotalForRoom);
    }
}
