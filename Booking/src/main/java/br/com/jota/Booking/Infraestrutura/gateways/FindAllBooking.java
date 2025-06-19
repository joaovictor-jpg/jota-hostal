package br.com.jota.Booking.Infraestrutura.gateways;

import br.com.jota.Booking.Infraestrutura.mappers.BookingEntityMapper;
import br.com.jota.Booking.Infraestrutura.repository.BookingRepositoryJpa;
import br.com.jota.Booking.domain.entity.Booking;

import java.util.List;

public class FindAllBooking implements br.com.jota.Booking.application.gateways.FindAllBooking {

    private final BookingRepositoryJpa repositoryJpa;

    public FindAllBooking(BookingRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public List<Booking> execute() {
        return repositoryJpa.findAll().stream().map(BookingEntityMapper::toBooking).toList();
    }
}
