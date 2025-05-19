package br.com.jota.payment.dto;

import br.com.jota.payment.entity.PaymentType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record ConfirmPayment(
        @NotNull(message = "ID da reserva não pode ser nulo")
        UUID idBooking,
        @NotBlank(message = "Número do cartão é obrigatório")
        @Size(min = 13, max = 19, message = "Número do cartão deve ter entre 13 e 19 dígitos")
        @Pattern(regexp = "^[0-9]+$", message = "Número do cartão deve conter apenas dígitos")
        String cardNumber,
        @NotNull(message = "Data de validade do cartão é obrigatória")
        @Future(message = "Data de validade do cartão deve ser futura")
        LocalDate cardValidity,
        @NotNull(message = "Tipo de pagamento é obrigatório")
        PaymentType paymentType,
        @NotBlank(message = "CVV do cartão é obrigatório")
        @Size(min = 3, max = 4, message = "CVV deve ter 3 ou 4 dígitos")
        @Pattern(regexp = "^[0-9]+$", message = "CVV deve conter apenas dígitos")
        String cardCVV
) {
}
