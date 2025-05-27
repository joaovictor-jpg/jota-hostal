package br.com.jota.Booking.Infraestrutura.mappers;

import br.com.jota.Booking.Infraestrutura.repository.BookingEntity;
import br.com.jota.Booking.domain.entity.Booking;

public class BookingEntityMapper {
    public BookingEntity toEntity(Booking booking) {
        return new BookingEntity(booking.getRoomNumber(), booking.getEmail(), booking.getNameGuest(), booking.getTotalPrice(), booking.getTelephone(),
                booking.getMessage(), booking.getStatus(), booking.getCheckIn(), booking.getCheckOut(), booking.getGuestCpf());
    }
}
