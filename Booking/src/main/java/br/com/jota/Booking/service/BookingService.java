package br.com.jota.Booking.service;

import br.com.jota.Booking.dtos.*;
import br.com.jota.Booking.entity.Booking;
import br.com.jota.Booking.entity.BookingStatus;
import br.com.jota.Booking.exception.BusinessRuleException;
import br.com.jota.Booking.http.RoomClient;
import br.com.jota.Booking.repository.BookingRepository;
import br.com.jota.Booking.service.validation.DeadlinesForBookingRoom;
import br.com.jota.Booking.service.validation.EntryAndExitDateValidation;
import br.com.jota.Booking.service.validation.IValidationDate;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.jota.Booking.entity.BookingStatus.PREPARING;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RabbitTemplate rabbitTemplate;
    private final RoomClient roomClient;
    private final List<IValidationDate> validationDates = new ArrayList<>();

    public BookingService(BookingRepository bookingRepository, RabbitTemplate rabbitTemplate, RoomClient roomClient) {
        this.bookingRepository = bookingRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.roomClient = roomClient;
        validationDates.add(new DeadlinesForBookingRoom());
        validationDates.add(new EntryAndExitDateValidation());
    }

    public void reservation(CreatedBooking createdBooking) {

        validationDates.forEach(v -> v.validationData(createdBooking.checkIn(), createdBooking.checkOut()));

        RoomDetails room = roomClient.getRoom(createdBooking.roomNumber());

        BigDecimal valueTotalForRoom = room.rentalValue().add(room.condoFee());
        BigDecimal interestRate = new BigDecimal("0.10");

//        if (!room.status().equals(Status.ACTIVE)) {
//            throw new BusinessRuleException("Quarto em Ocupado");
//        }

        if (createdBooking.checkIn().getDayOfWeek().equals(SATURDAY) || createdBooking.checkIn().getDayOfWeek().equals(SUNDAY)) {
            valueTotalForRoom = valueTotalForRoom.multiply(interestRate);
        }

        Booking booking = new Booking(room.roomNumber(), createdBooking.email(), createdBooking.nameGuest(), valueTotalForRoom, createdBooking.telephone(), createdBooking.message(),
                BookingStatus.PENDING, createdBooking.checkIn(), createdBooking.checkOut(), createdBooking.guestCpf());

        bookingRepository.save(booking);

        var bookingMessage = new BookingMessagem(booking.getId(), booking.getRoomNumber());

        rabbitTemplate.convertAndSend("ReservationRequested", bookingMessage);
    }

    public void updateStatusBooking(RoomMessagem messagem) {
        Booking booking = bookingRepository.findByIdAndRoomNumber(messagem.idBooking(), messagem.roomNumber()).orElseThrow(() -> new EntityNotFoundException("reservation not found"));

        booking.setStatus(PREPARING);

        bookingRepository.save(booking);
    }

    public List<BookingDetails> listBooking() {
        return bookingRepository.findAll().stream().map(BookingDetails::new).toList();
    }

}
