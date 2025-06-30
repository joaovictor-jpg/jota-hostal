package br.com.jota.Booking.Infraestrutura.gateways;

import br.com.jota.Booking.Infraestrutura.dtos.BookingMessagem;
import br.com.jota.Booking.Infraestrutura.repository.BookingRepositoryJpa;
import br.com.jota.Booking.application.gateways.Delete;
import br.com.jota.Booking.exception.BusinessRuleException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

import static br.com.jota.Booking.domain.enums.BookingStatus.CANCELLED;

public class DeleteCase implements Delete {

    private final BookingRepositoryJpa repositoryJpa;
    private final RabbitTemplate rabbitTemplate;

    public DeleteCase(BookingRepositoryJpa repositoryJpa, RabbitTemplate rabbitTemplate) {
        this.repositoryJpa = repositoryJpa;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void execute(UUID idBooking) {
        var booking = repositoryJpa.findById(idBooking)
                .orElseThrow(() -> new BusinessRuleException("Booking not found"));

        booking.setStatus(CANCELLED);

        var bookingMessageCancelled = new BookingMessagem(booking.getId(), booking.getRoomNumber());

        rabbitTemplate.convertAndSend("CancelRequested", bookingMessageCancelled);

        repositoryJpa.save(booking);
    }
}
