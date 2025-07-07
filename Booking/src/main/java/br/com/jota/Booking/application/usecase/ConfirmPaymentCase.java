package br.com.jota.Booking.application.usecase;

import br.com.jota.Booking.application.gateways.ConfirmPayment;
import br.com.jota.Booking.application.gateways.PaymentApproved;

import java.util.UUID;

public class ConfirmPaymentCase implements ConfirmPayment {
    private final PaymentApproved paymentApproved;

    public ConfirmPaymentCase(PaymentApproved paymentApproved) {
        this.paymentApproved = paymentApproved;
    }

    @Override
    public void execute(UUID idBooking) {

    }
}
