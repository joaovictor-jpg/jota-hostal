package br.com.jota.Booking.application.usecase;

import br.com.jota.Booking.application.gateways.FindAllBooking;
import br.com.jota.Booking.application.gateways.ListBooking;
import br.com.jota.Booking.domain.entity.Booking;

import java.util.List;

public class ListBookingCase implements ListBooking {

    private final FindAllBooking findAllBooking;

    public ListBookingCase(FindAllBooking findAllBooking) {
        this.findAllBooking = findAllBooking;
    }

    @Override
    public List<Booking> execute() {
        return findAllBooking.execute();
    }
}
