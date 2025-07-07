package br.com.jota.Booking.Infraestrutura.gateways;

import br.com.jota.Booking.Infraestrutura.dtos.SendEmail;
import br.com.jota.Booking.Infraestrutura.repository.BookingEntity;
import br.com.jota.Booking.Infraestrutura.repository.BookingRepositoryJpa;
import br.com.jota.Booking.application.gateways.PaymentApproved;
import br.com.jota.Booking.exception.BusinessRuleException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

import static br.com.jota.Booking.domain.enums.BookingStatus.CONFIRMED;

public class PaymentApprovedCase implements PaymentApproved {

    private final RabbitTemplate rabbitTemplate;
    private final BookingRepositoryJpa repositoryJpa;

    public PaymentApprovedCase(RabbitTemplate rabbitTemplate, BookingRepositoryJpa repositoryJpa) {
        this.rabbitTemplate = rabbitTemplate;
        this.repositoryJpa = repositoryJpa;
    }


    @Override
    public void execute(UUID idBooking) {
        BookingEntity booking = repositoryJpa.findById(idBooking)
                .orElseThrow(() -> new BusinessRuleException("Booking not found"));

        booking.setStatus(CONFIRMED);

        var sendMessage = new SendEmail(booking.getEmail(), booking.getRoomNumber(),booking.getNameGuest(),
                "Seu quarto está recervado", booking.getCheckIn(), booking.getCheckOut());

        rabbitTemplate.convertAndSend("ReservationConfirmed", sendMessage);

        repositoryJpa.save(booking);
    }
}
