package br.com.jota.room.server.dto;

import br.com.jota.room.server.entity.Status;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreatedRoom(
        @NotNull(message = "Área do quarto é obrigatória")
        @DecimalMin(value = "1.0", message = "Área deve ser maior que zero")
        BigDecimal areaM2,
        @NotNull(message = "Número do quarto é obrigatório")
        @Min(value = 1, message = "Número do quarto deve ser maior que zero")
        Integer roomNumber,
        @NotNull(message = "Valor do aluguel é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "Valor do aluguel deve ser maior que zero")
        BigDecimal rentalValue,
        @DecimalMin(value = "0.0", message = "Taxa de condomínio não pode ser negativa")
        BigDecimal condoFee,
        @NotNull(message = "Informar se aceita animais é obrigatório")
        Boolean acceptsAnimals,
        @NotBlank(message = "Endereço é obrigatório")
        @Size(max = 255, message = "Endereço deve ter no máximo 255 caracteres")
        String address,
        @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
        String description,
        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(
                regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-\\d{4}",
                message = "Formato de telefone inválido"
        )
        String telephone,
        @NotNull(message = "Status é obrigatório")
        Status status
) {
}
