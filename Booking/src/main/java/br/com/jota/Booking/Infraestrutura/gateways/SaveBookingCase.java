package br.com.jota.Booking.Infraestrutura.gateways;

import br.com.jota.Booking.Infraestrutura.dtos.BookingMessagem;
import br.com.jota.Booking.Infraestrutura.dtos.PaymentCreation;
import br.com.jota.Booking.Infraestrutura.mappers.BookingEntityMapper;
import br.com.jota.Booking.Infraestrutura.repository.BookingRepositoryJpa;
import br.com.jota.Booking.application.gateways.SaveBooking;
import br.com.jota.Booking.domain.entity.Booking;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SaveBookingCase implements SaveBooking {
    private final BookingRepositoryJpa repositoryJpa;
    private final BookingEntityMapper mapper;
    private final RabbitTemplate rabbitTemplate;

    public SaveBookingCase(BookingRepositoryJpa repositoryJpa, BookingEntityMapper mapper, RabbitTemplate rabbitTemplate) {
        this.repositoryJpa = repositoryJpa;
        this.mapper = mapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void execute(Booking booking) {
        var entity = mapper.toEntity(booking);
        repositoryJpa.save(entity);

        var bookingMessage = new BookingMessagem(entity.getId(), entity.getRoomNumber());
        var paymentCreation = new PaymentCreation(entity.getId(), entity.getEmail(),
                entity.getNameGuest(), entity.getTotalPrice(), entity.getTelephone(), entity.getGuestCpf());

        rabbitTemplate.convertAndSend("ReservationRequested", bookingMessage);
        rabbitTemplate.convertAndSend("ReservationCreated", paymentCreation);
    }
}
