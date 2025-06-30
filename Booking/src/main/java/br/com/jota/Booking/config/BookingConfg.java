package br.com.jota.Booking.config;

import br.com.jota.Booking.Infraestrutura.gateways.DeleteCase;
import br.com.jota.Booking.Infraestrutura.gateways.FindAllBookingCase;
import br.com.jota.Booking.Infraestrutura.gateways.SaveBookingCase;
import br.com.jota.Booking.Infraestrutura.gateways.VerifyRoomAvailableCase;
import br.com.jota.Booking.Infraestrutura.http.RoomClient;
import br.com.jota.Booking.Infraestrutura.mappers.BookingEntityMapper;
import br.com.jota.Booking.Infraestrutura.mappers.BookingMapper;
import br.com.jota.Booking.Infraestrutura.repository.BookingRepositoryJpa;
import br.com.jota.Booking.application.gateways.*;
import br.com.jota.Booking.application.usecase.CreateReservationCase;
import br.com.jota.Booking.application.usecase.DeleteBookingCase;
import br.com.jota.Booking.application.usecase.ListBookingCase;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class BookingConfg {
    @Bean
    public CreateReservation createReservation(SaveBooking saveBooking, VerifyRoomAvailable verifyRoomAvailable) {
        return new CreateReservationCase(saveBooking, verifyRoomAvailable);
    }

    @Bean
    public ListBooking listBooking(FindAllBooking findAllBooking) {
        return new ListBookingCase(findAllBooking);
    }

    @Bean
    public FindAllBooking findAllBooking(BookingRepositoryJpa repositoryJpa) {
        return new FindAllBookingCase(repositoryJpa);
    }

    @Bean
    public BookingMapper bookingMapper() {
        return new BookingMapper();
    }

    @Bean
    public SaveBooking saveBooking(BookingRepositoryJpa repositoryJpa, BookingEntityMapper mapper, RabbitTemplate rabbitTemplate) {
        return new SaveBookingCase(repositoryJpa, mapper, rabbitTemplate);
    }

    @Bean
    public DeleteBooking deleteBooking(Delete Delete) {
        return new DeleteBookingCase(Delete);
    }

    @Bean
    public Delete delete(BookingRepositoryJpa repositoryJpa, RabbitTemplate rabbitTemplate) {
        return new DeleteCase(repositoryJpa, rabbitTemplate);
    }

    @Bean
    public BookingEntityMapper bookingEntityMapper() {
        return new BookingEntityMapper();
    }

    @Bean VerifyRoomAvailable verifyRoomAvailable(RoomClient roomClient) {
        return new VerifyRoomAvailableCase(roomClient);
    }
}
