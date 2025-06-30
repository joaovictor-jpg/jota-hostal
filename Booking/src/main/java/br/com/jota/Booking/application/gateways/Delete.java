package br.com.jota.Booking.application.gateways;

import java.util.UUID;

public interface Delete {
    void execute(UUID idBooking);
}
