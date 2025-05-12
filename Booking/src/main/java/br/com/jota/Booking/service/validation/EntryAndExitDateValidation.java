package br.com.jota.Booking.service.validation;

import br.com.jota.Booking.exception.BusinessRuleException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EntryAndExitDateValidation implements IValidationDate{
    @Override
    public void validationData(LocalDateTime checkIn, LocalDateTime checkOut) {
        if (checkIn.isAfter(checkOut)) {
            throw new BusinessRuleException("A data de Check-out deve ser depois de check-in");
        }
    }
}
