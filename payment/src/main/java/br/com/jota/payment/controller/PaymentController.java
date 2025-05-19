package br.com.jota.payment.controller;

import br.com.jota.payment.dto.PaymentResponse;
import br.com.jota.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> listPayment() {
        return ResponseEntity.ok().body(paymentService.listPayment());
    }

    @DeleteMapping("/{idBooking}")
    public ResponseEntity<Void> deletePayment(@PathVariable("idBooking")UUID idBooking) {
        paymentService.deletePayment(idBooking);
        return ResponseEntity.noContent().build();
    }
}
