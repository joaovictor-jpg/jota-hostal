package br.com.jota.Booking.controller;

import br.com.jota.Booking.dtos.CreatedBooking;
import br.com.jota.Booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<String> reservetion(@RequestBody @Valid CreatedBooking createdBooking) {
        bookingService.reservation(createdBooking);
        return ResponseEntity.ok().body("Seu quarto está sendo preparado");
    }
}
