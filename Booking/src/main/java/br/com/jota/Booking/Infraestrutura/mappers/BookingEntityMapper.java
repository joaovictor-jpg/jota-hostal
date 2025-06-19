package br.com.jota.Booking.Infraestrutura.mappers;

import br.com.jota.Booking.Infraestrutura.repository.BookingEntity;
import br.com.jota.Booking.domain.entity.Booking;

public class BookingEntityMapper {
    public BookingEntity toEntity(Booking booking) {
        return new BookingEntity(booking.getRoomNumber(), booking.getEmail(), booking.getNameGuest(), booking.getTotalPrice(), booking.getTelephone(),
                booking.getMessage(), booking.getStatus(), booking.getCheckIn(), booking.getCheckOut(), booking.getGuestCpf());
    }

    public static Booking toBooking(BookingEntity entity) {
        return new Booking(entity.getId(), entity.getRoomNumber(), entity.getMessage(), entity.getNameGuest(),
                entity.getTotalPrice(), entity.getTelephone(), entity.getMessage(), entity.getStatus(), entity.getCheckIn(),
                entity.getCheckOut(), entity.getGuestCpf());
    }
}
