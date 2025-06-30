package br.com.jota.Booking.application.usecase;

import br.com.jota.Booking.application.gateways.Delete;
import br.com.jota.Booking.application.gateways.DeleteBooking;

import java.util.UUID;

public class DeleteBookingCase implements DeleteBooking {

    private final Delete delete;

    public DeleteBookingCase(Delete delete) {
        this.delete = delete;
    }

    @Override
    public void execute(UUID idBooking) {
        delete.execute(idBooking);
    }
}
