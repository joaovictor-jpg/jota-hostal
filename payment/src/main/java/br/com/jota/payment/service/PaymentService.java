package br.com.jota.payment.service;

import br.com.jota.payment.dto.PaymentCreation;
import br.com.jota.payment.dto.PaymentMessage;
import br.com.jota.payment.dto.PaymentResponse;
import br.com.jota.payment.entity.Payment;
import br.com.jota.payment.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static br.com.jota.payment.entity.PaymentStatus.CANCELED;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RabbitTemplate rabbitTemplate;

    public PaymentService(PaymentRepository paymentRepository, RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.rabbitTemplate = rabbitTemplate;
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

    public Payment findByBooking(UUID idBooking) {
        return paymentRepository.findByIdBooking(idBooking).orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    }

    public void deletePayment(UUID idBooking) {
        var payment = this.findByBooking(idBooking);
        payment.setStatus(CANCELED);
        paymentRepository.save(payment);

        var paymentMessage = new PaymentMessage("Payment canceled", payment.getIdBooking());

        rabbitTemplate.convertAndSend("CancelPayment", paymentMessage);
    }
}
