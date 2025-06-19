package br.com.jota.Booking.application.gateways;

import br.com.jota.Booking.domain.entity.Booking;

import java.util.List;

public interface ListBooking {
    List<Booking> execute();
}
