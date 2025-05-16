package br.com.jota.Booking.repository;

import br.com.jota.Booking.entity.Booking;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByIdAndRoomNumber(UUID id, Integer roomNumber);

    List<Booking> findByGuestCpf(String s);

    List<Booking> findByEmail(String email);
}
