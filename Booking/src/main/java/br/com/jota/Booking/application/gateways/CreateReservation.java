package br.com.jota.Booking.application.gateways;

import br.com.jota.Booking.domain.entity.Booking;

public interface CreateReservation {
    void execute(Booking booking);
}
