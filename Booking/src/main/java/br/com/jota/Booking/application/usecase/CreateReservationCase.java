package br.com.jota.Booking.application.usecase;

import br.com.jota.Booking.application.gateways.CreateReservation;
import br.com.jota.Booking.application.gateways.VerifyRoomAvailable;
import br.com.jota.Booking.application.gateways.SaveBooking;
import br.com.jota.Booking.domain.entity.Booking;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class CreateReservationCase implements CreateReservation {

    private final SaveBooking saveBooking;
    private final VerifyRoomAvailable verifyRoomAvailable;

    public CreateReservationCase(SaveBooking saveBooking, VerifyRoomAvailable verifyRoomAvailable) {
        this.saveBooking = saveBooking;
        this.verifyRoomAvailable = verifyRoomAvailable;
    }

    @Override
    public void execute(Booking booking) {

        verifyRoomAvailable.execute(booking);

        BigDecimal basePrice = booking.getTotalPrice();
        BigDecimal weekendRate = new BigDecimal("0.10");

        if (isWeekend(booking.getCheckIn())) {
            BigDecimal additionalCharge = basePrice.multiply(weekendRate);
            booking.setTotalPrice(basePrice.add(additionalCharge));
        }

        saveBooking.execute(booking);
    }

    private boolean isWeekend(LocalDateTime date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}
