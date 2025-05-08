package br.com.jota.room.server.dto;

import br.com.jota.room.server.entity.Status;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateRoom(
        @DecimalMin(value = "1.0", message = "Área deve ser maior que zero")
        BigDecimal areaM2,
        @Min(value = 1, message = "Número do quarto deve ser maior que zero")
        Integer roomNumber,
        @DecimalMin(value = "0.0", inclusive = false, message = "Valor do aluguel deve ser maior que zero")
        BigDecimal rentalValue,
        @DecimalMin(value = "0.0", message = "Taxa de condomínio não pode ser negativa")
        BigDecimal condoFee,
        Boolean acceptsAnimals,
        @Size(max = 255, message = "Endereço deve ter no máximo 255 caracteres")
        String address,
        @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
        String description,
        @Pattern(
                regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-\\d{4}",
                message = "Formato de telefone inválido"
        )
        String telephone
) {
}
