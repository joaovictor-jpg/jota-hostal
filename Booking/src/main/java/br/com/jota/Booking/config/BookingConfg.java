package br.com.jota.Booking.config;

import br.com.jota.Booking.Infraestrutura.gateways.*;
import br.com.jota.Booking.Infraestrutura.http.RoomClient;
import br.com.jota.Booking.Infraestrutura.mappers.BookingEntityMapper;
import br.com.jota.Booking.Infraestrutura.mappers.BookingMapper;
import br.com.jota.Booking.Infraestrutura.repository.BookingRepositoryJpa;
import br.com.jota.Booking.application.gateways.*;
import br.com.jota.Booking.application.usecase.ConfirmPaymentCase;
import br.com.jota.Booking.application.usecase.CreateReservationCase;
import br.com.jota.Booking.application.usecase.DeleteBookingCase;
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
    public ConfirmPayment confirmPayment(PaymentApproved paymentApproved) {
        return new ConfirmPaymentCase(paymentApproved);
    }

    @Bean
    public PaymentApproved paymentApproved(RabbitTemplate rabbitTemplate, BookingRepositoryJpa repositoryJpa) {
        return new PaymentApprovedCase(rabbitTemplate, repositoryJpa);
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
