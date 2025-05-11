package br.com.jota.Booking.service;

import br.com.jota.Booking.dtos.CreatedBooking;
import br.com.jota.Booking.dtos.RoomDetails;
import br.com.jota.Booking.dtos.Status;
import br.com.jota.Booking.exception.BusinessRuleException;
import br.com.jota.Booking.http.RoomClient;
import br.com.jota.Booking.repository.BookingRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RabbitTemplate rabbitTemplate;
    private final RoomClient roomClient;

    public BookingService(BookingRepository bookingRepository, RabbitTemplate rabbitTemplate, RoomClient roomClient) {
        this.bookingRepository = bookingRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.roomClient = roomClient;
    }

    public void reservation(CreatedBooking createdBooking) {

        if (createdBooking.checkIn().isAfter(createdBooking.checkOut())) {
            throw new BusinessRuleException("A data de Check-out deve ser depois de check-in");
        }

        long daysBetween = ChronoUnit.DAYS.between(
                createdBooking.checkIn(),
                createdBooking.checkOut()
        );

        if (daysBetween < 1) {
            throw new BusinessRuleException("Minimum stay is 1 day");
        }

        if (daysBetween > 30) {
            throw new BusinessRuleException("Maximum stay is 30 days");
        }

        RoomDetails room = roomClient.getRoom(createdBooking.roomNumber());

        BigDecimal valueRoom = room.rentalValue().add(room.condoFee());
        BigDecimal fees = new BigDecimal("0.10");

        if (room.status().equals(Status.INACTIVE)) {
            throw new BusinessRuleException("Quarto em Ocupado");
        }

        if (createdBooking.checkIn().getDayOfWeek().equals(SATURDAY) || createdBooking.checkIn().getDayOfWeek().equals(SUNDAY)) {
            valueRoom = valueRoom.multiply(fees);
        }
    }

}
