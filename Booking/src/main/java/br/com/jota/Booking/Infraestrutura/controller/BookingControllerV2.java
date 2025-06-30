package br.com.jota.Booking.Infraestrutura.controller;

import br.com.jota.Booking.Infraestrutura.dtos.BookingDetails;
import br.com.jota.Booking.Infraestrutura.dtos.CreatedBooking;
import br.com.jota.Booking.Infraestrutura.mappers.BookingMapper;
import br.com.jota.Booking.application.gateways.CreateReservation;
import br.com.jota.Booking.application.gateways.DeleteBooking;
import br.com.jota.Booking.application.gateways.ListBooking;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("bookings/v2")
public class BookingControllerV2 {
    private final CreateReservation createReservation;
    private final ListBooking listBooking;
    private final BookingMapper mapper;
    private final DeleteBooking deleteBooking;

    public BookingControllerV2(CreateReservation createReservation, ListBooking listBooking, BookingMapper mapper, BookingMapper bookingMapper, DeleteBooking deleteBooking) {
        this.createReservation = createReservation;
        this.listBooking = listBooking;
        this.mapper = mapper;
        this.deleteBooking = deleteBooking;
    }

    @PostMapping
    public ResponseEntity<String> reservetion(@RequestBody @Valid CreatedBooking createdBooking) {
        createReservation.execute(mapper.fromCreateBookingToBooking(createdBooking));
        return ResponseEntity.ok().body("Seu quarto está sendo preparado");
    }

    @GetMapping
    public ResponseEntity<List<BookingDetails>> listBooking() {
        List<BookingDetails> bookingDetailsList = listBooking.execute().stream().map(BookingMapper::BookingToBookingDetails).toList();
        return ResponseEntity.ok().body(bookingDetailsList);
    }

    @DeleteMapping("/{idbooking}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("idbooking") UUID idbooking) {
        deleteBooking.execute(idbooking);
        return ResponseEntity.noContent().build();
    }
}
