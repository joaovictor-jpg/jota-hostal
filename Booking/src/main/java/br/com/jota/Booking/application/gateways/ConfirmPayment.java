package br.com.jota.Booking.application.gateways;

import java.util.UUID;

public interface ConfirmPayment {
    void execute(UUID idBooking);
}
