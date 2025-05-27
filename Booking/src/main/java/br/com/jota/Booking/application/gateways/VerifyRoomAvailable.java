package br.com.jota.Booking.application.gateways;

import br.com.jota.Booking.domain.entity.Booking;

public interface VerifyRoomAvailable {
    void execute(Booking booking);
}
