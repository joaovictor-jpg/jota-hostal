package br.com.jota.Booking.application.gateways;

import java.util.UUID;

public interface PaymentApproved {
    void execute(UUID idBooking);
}
