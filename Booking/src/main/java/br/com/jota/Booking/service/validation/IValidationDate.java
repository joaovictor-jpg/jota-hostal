package br.com.jota.Booking.service.validation;

import java.time.LocalDateTime;

public interface IValidationDate {
    void validationData(LocalDateTime checkIn, LocalDateTime checkOut);
}
