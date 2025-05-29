package br.com.jota.Booking.application.usecase;

import br.com.jota.Booking.application.gateways.SaveBooking;
import br.com.jota.Booking.application.gateways.VerifyRoomAvailable;
import br.com.jota.Booking.domain.entity.Booking;
import br.com.jota.Booking.domain.enums.BookingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateReservationCaseTest {

    @Mock
    private SaveBooking saveBooking;
    @Mock
    private VerifyRoomAvailable verifyRoomAvailable;
    @InjectMocks
    private CreateReservationCase createReservationCase;

    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking(
                null,
                101,
                "guest@example.com",
                "John Doe",
                BigDecimal.valueOf(100.00),
                "+5511999999999",
                "Test reservation",
                BookingStatus.PENDING,
                LocalDateTime.of(2023, Month.JUNE, 15, 14, 0),
                LocalDateTime.of(2023, Month.JUNE, 17, 12, 0),
                "12345678901"
        );
    }

    @Test
    void shouldCreateReservationSuccessfully() {
        // Act
        createReservationCase.execute(booking);

        // Assert
        verify(verifyRoomAvailable, times(1)).execute(booking);
        verify(saveBooking, times(1)).execute(booking);
    }

    @Test
    void shouldApplyWeekendSurchargeWhenCheckInIsOnWeekend() {
        booking = new Booking(
                null,
                101,
                "guest@example.com",
                "John Doe",
                BigDecimal.valueOf(100.00),
                "+5511999999999",
                "Test reservation",
                BookingStatus.PENDING,
                LocalDateTime.of(2023, Month.JUNE, 17, 14, 0),
                LocalDateTime.of(2023, Month.JUNE, 19, 12, 0),
                "12345678901"
        );

        createReservationCase.execute(booking);

        BigDecimal expectedPrice = BigDecimal.valueOf(110.00);
        assertEquals(0, expectedPrice.compareTo(booking.getTotalPrice()));
        verify(saveBooking, times(1)).execute(booking);
    }

    @Test
    void shouldVerifyRoomAvailabilityBeforeSaving() {
        createReservationCase.execute(booking);

        InOrder inOrder = inOrder(verifyRoomAvailable, saveBooking);
        inOrder.verify(verifyRoomAvailable).execute(booking);
        inOrder.verify(saveBooking).execute(booking);
    }

}