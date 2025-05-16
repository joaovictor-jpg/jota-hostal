package br.com.jota.Booking.dtos;

import br.com.jota.Booking.exception.BusinessRuleException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FindByCpfOrEmail(
        @Email(message = "Por favor, forneça um endereço de email válido")
        @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
        String email,

        @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String guestCpf
) {
    public FindByCpfOrEmail {
        if ((email == null || email.isBlank()) &&
                (guestCpf == null || guestCpf.isBlank())) {
            throw new BusinessRuleException("Pelo menos um dos campos (email ou CPF) deve ser fornecido");
        }
    }
}
