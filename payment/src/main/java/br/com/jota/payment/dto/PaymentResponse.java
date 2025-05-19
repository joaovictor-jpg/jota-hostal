package br.com.jota.payment.dto;

import br.com.jota.payment.entity.Payment;
import br.com.jota.payment.entity.PaymentStatus;
import br.com.jota.payment.entity.PaymentType;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResponse(
        Long id,
        UUID idBooking,
        String guestName,
        String guestEmail,
        BigDecimal paymentValue,
        PaymentType paymentType,
        String guestCpf,
        String country,
        String telephone,
        PaymentStatus status
) {
    public PaymentResponse(Payment payment) {
        this(payment.getId(), payment.getIdBooking(), payment.getGuestName(), payment.getGuestEmail(),
                payment.getPaymentValue(), payment.getPaymentType(), payment.getGuestCpf(), payment.getCountry(), payment.getTelephone(),
                payment.getStatus());
    }
}
