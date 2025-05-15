package br.com.jota.Booking.repository;

import br.com.jota.Booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByIdAndRoomNumber(UUID id, Integer roomNumber);
}
