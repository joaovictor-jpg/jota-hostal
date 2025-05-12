package br.com.jota.Booking.service.validation;

import br.com.jota.Booking.exception.BusinessRuleException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class DeadlinesForBookingRoom implements IValidationDate{
    @Override
    public void validationData(LocalDateTime checkIn, LocalDateTime checkOut) {
        long daysBetween = ChronoUnit.DAYS.between(
                checkIn,
                checkOut
        );

        if (daysBetween < 1) {
            throw new BusinessRuleException("A estadia mínima é de 1 dia");
        }

        if (daysBetween > 30) {
            throw new BusinessRuleException("A estadia máxima é de 30 dias");
        }
    }
}
