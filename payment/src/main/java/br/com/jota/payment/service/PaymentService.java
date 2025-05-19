package br.com.jota.payment.service;

import br.com.jota.payment.dto.PaymentCreation;
import br.com.jota.payment.dto.PaymentResponse;
import br.com.jota.payment.entity.Payment;
import br.com.jota.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void createdPayment(PaymentCreation paymentCreation) {
        var payment = new Payment(paymentCreation.idBooking(), paymentCreation.nameGuest(),
                paymentCreation.email(), paymentCreation.totalPrice(), paymentCreation.telephone(),
                paymentCreation.guestCpf());
        paymentRepository.save(payment);
    }

    public List<PaymentResponse> listPayment() {
        return paymentRepository.findAll().stream().map(PaymentResponse::new).toList();
    }
}
