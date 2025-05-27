package br.com.jota.Booking.Infraestrutura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepositoryJpa extends JpaRepository<BookingEntity, UUID> {
}
