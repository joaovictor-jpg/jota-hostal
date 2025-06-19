package br.com.jota.Booking.application.usecase;

import br.com.jota.Booking.application.gateways.ListBooking;
import br.com.jota.Booking.domain.entity.Booking;

import java.util.List;

public class ListBookingCase implements ListBooking {
    @Override
    public List<Booking> execute() {
        return List.of();
    }
}
