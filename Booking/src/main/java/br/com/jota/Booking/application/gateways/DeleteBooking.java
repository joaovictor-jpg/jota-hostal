package br.com.jota.Booking.application.gateways;

import java.util.UUID;

public interface DeleteBooking {
    void execute(UUID idBooking);
}
