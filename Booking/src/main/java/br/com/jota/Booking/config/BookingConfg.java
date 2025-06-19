package br.com.jota.Booking.config;

import br.com.jota.Booking.Infraestrutura.gateways.SaveBookingCase;
import br.com.jota.Booking.Infraestrutura.gateways.VerifyRoomAvailableCase;
import br.com.jota.Booking.Infraestrutura.http.RoomClient;
import br.com.jota.Booking.Infraestrutura.mappers.BookingEntityMapper;
import br.com.jota.Booking.Infraestrutura.mappers.BookingMapper;
import br.com.jota.Booking.Infraestrutura.repository.BookingRepositoryJpa;
import br.com.jota.Booking.application.gateways.CreateReservation;
import br.com.jota.Booking.application.gateways.ListBooking;
import br.com.jota.Booking.application.gateways.SaveBooking;
import br.com.jota.Booking.application.gateways.VerifyRoomAvailable;
import br.com.jota.Booking.application.usecase.CreateReservationCase;
import br.com.jota.Booking.application.usecase.ListBookingCase;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingConfg {
    @Bean
    public CreateReservation createReservation(SaveBooking saveBooking, VerifyRoomAvailable verifyRoomAvailable) {
        return new CreateReservationCase(saveBooking, verifyRoomAvailable);
    }

    @Bean
    public ListBooking listBooking() {
        return new ListBookingCase();
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
    public BookingEntityMapper bookingEntityMapper() {
        return new BookingEntityMapper();
    }

    @Bean VerifyRoomAvailable verifyRoomAvailable(RoomClient roomClient) {
        return new VerifyRoomAvailableCase(roomClient);
    }
}
