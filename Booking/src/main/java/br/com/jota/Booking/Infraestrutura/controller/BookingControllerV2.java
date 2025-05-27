package br.com.jota.Booking.Infraestrutura.controller;

import br.com.jota.Booking.Infraestrutura.dtos.CreatedBooking;
import br.com.jota.Booking.Infraestrutura.mappers.BookingMapper;
import br.com.jota.Booking.application.gateways.CreateReservation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookings/v2")
public class BookingControllerV2 {
    private final CreateReservation createReservation;
    private final BookingMapper mapper;

    public BookingControllerV2(CreateReservation createReservation, BookingMapper mapper) {
        this.createReservation = createReservation;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<String> reservetion(@RequestBody @Valid CreatedBooking createdBooking) {
        createReservation.execute(mapper.fromCreateBookingToBooking(createdBooking));
        return ResponseEntity.ok().body("Seu quarto está sendo preparado");
    }
}
