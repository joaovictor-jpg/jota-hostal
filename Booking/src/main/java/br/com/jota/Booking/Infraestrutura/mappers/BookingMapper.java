package br.com.jota.Booking.Infraestrutura.mappers;

import br.com.jota.Booking.Infraestrutura.dtos.BookingDetails;
import br.com.jota.Booking.Infraestrutura.dtos.CreatedBooking;
import br.com.jota.Booking.domain.entity.Booking;
import br.com.jota.Booking.domain.enums.BookingStatus;

import java.math.BigDecimal;

public class BookingMapper {
    public Booking fromCreateBookingToBooking(CreatedBooking createdBooking) {
        return new Booking(
                createdBooking.roomNumber(),
                createdBooking.email(),
                createdBooking.nameGuest(),
                BigDecimal.ZERO,
                createdBooking.telephone(),
                createdBooking.message(),
                BookingStatus.PENDING,
                createdBooking.checkIn(),
                createdBooking.checkOut(),
                createdBooking.guestCpf()
        );
    }

    public static BookingDetails BookingToBookingDetails(Booking booking) {
        return new BookingDetails(booking);
    }
}
