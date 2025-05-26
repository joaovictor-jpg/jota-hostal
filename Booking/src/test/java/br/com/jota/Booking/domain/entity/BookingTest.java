package br.com.jota.Booking.domain.entity;

import br.com.jota.Booking.domain.enums.BookingStatus;
import br.com.jota.Booking.domain.excption.BusinessRuleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private static final UUID TEST_ID = UUID.randomUUID();
    private static final Integer TEST_ROOM_NUMBER = 101;
    private static final String TEST_EMAIL = "guest@example.com";
    private static final String TEST_NAME = "John Doe";
    private static final BigDecimal TEST_PRICE = BigDecimal.valueOf(150.50);
    private static final String TEST_PHONE = "+5511999999999";
    private static final String TEST_MESSAGE = "Test message";
    private static final String TEST_CPF = "12345678901";
    private static final LocalDateTime TEST_CHECK_IN = LocalDateTime.now().plusDays(1);
    private static final LocalDateTime TEST_CHECK_OUT = LocalDateTime.now().plusDays(3);

    @Test
    void shouldCreateBookingWithValidParameters() {
        Booking booking = new Booking(
                TEST_ID,
                TEST_ROOM_NUMBER,
                TEST_EMAIL,
                TEST_NAME,
                TEST_PRICE,
                TEST_PHONE,
                TEST_MESSAGE,
                BookingStatus.CONFIRMED,
                TEST_CHECK_IN,
                TEST_CHECK_OUT,
                TEST_CPF
        );

        assertNotNull(booking);
        assertEquals(TEST_ID, booking.getId());
        assertEquals(TEST_ROOM_NUMBER, booking.getRoomNumber());
        assertEquals(TEST_EMAIL, booking.getEmail());
        assertEquals(TEST_NAME, booking.getNameGuest());
        assertEquals(TEST_PRICE, booking.getTotalPrice());
        assertEquals(TEST_PHONE, booking.getTelephone());
        assertEquals(TEST_MESSAGE, booking.getMessage());
        assertEquals(BookingStatus.CONFIRMED, booking.getStatus());
        assertEquals(TEST_CHECK_IN, booking.getCheckIn());
        assertEquals(TEST_CHECK_OUT, booking.getCheckOut());
        assertEquals(TEST_CPF, booking.getGuestCpf());
    }

    @Test
    void equalsShouldReturnTrueForSameId() {
        Booking booking1 = new Booking(
                TEST_ID,
                TEST_ROOM_NUMBER,
                TEST_EMAIL,
                TEST_NAME,
                TEST_PRICE,
                TEST_PHONE,
                TEST_MESSAGE,
                BookingStatus.CONFIRMED,
                TEST_CHECK_IN,
                TEST_CHECK_OUT,
                TEST_CPF
        );

        Booking booking2 = new Booking(
                TEST_ID,
                102,
                "other@email.com",
                "Other Name",
                BigDecimal.ZERO,
                "00000000000",
                "Other message",
                BookingStatus.CANCELLED,
                TEST_CHECK_IN.plusDays(10),
                TEST_CHECK_OUT.plusDays(10),
                "98765432109"
        );

        assertEquals(booking1, booking2);
        assertEquals(booking1.hashCode(), booking2.hashCode());
    }

    @Test
    void equalsShouldReturnFalseForDifferentId() {
        Booking booking1 = new Booking(
                TEST_ID,
                TEST_ROOM_NUMBER,
                TEST_EMAIL,
                TEST_NAME,
                TEST_PRICE,
                TEST_PHONE,
                TEST_MESSAGE,
                BookingStatus.CONFIRMED,
                TEST_CHECK_IN,
                TEST_CHECK_OUT,
                TEST_CPF
        );

        Booking booking2 = new Booking(
                UUID.randomUUID(), // different ID
                TEST_ROOM_NUMBER,
                TEST_EMAIL,
                TEST_NAME,
                TEST_PRICE,
                TEST_PHONE,
                TEST_MESSAGE,
                BookingStatus.CONFIRMED,
                TEST_CHECK_IN,
                TEST_CHECK_OUT,
                TEST_CPF
        );

        assertNotEquals(booking1, booking2);
    }

    @ParameterizedTest
    @MethodSource("invalidDateRangesProvider")
    void shouldThrowExceptionForInvalidDateRanges(LocalDateTime checkIn, LocalDateTime checkOut, String expectedMessage) {
        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () ->
                new Booking(
                        TEST_ID,
                        TEST_ROOM_NUMBER,
                        TEST_EMAIL,
                        TEST_NAME,
                        TEST_PRICE,
                        TEST_PHONE,
                        TEST_MESSAGE,
                        BookingStatus.CONFIRMED,
                        checkIn,
                        checkOut,
                        TEST_CPF
                ));

        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> invalidDateRangesProvider() {
        LocalDateTime now = LocalDateTime.now();
        return Stream.of(
                Arguments.of(now.plusDays(2), now.plusDays(1), "A data de Check-out deve ser depois de check-in"),
                Arguments.of(now, now.plusHours(23), "A estadia mínima é de 1 dia"),
                Arguments.of(now, now, "A estadia mínima é de 1 dia"),
                Arguments.of(now, now.plusDays(31), "A estadia máxima é de 30 dias")
        );
    }

    @Test
    void shouldAllowMaximumStayOf30Days() {
        LocalDateTime checkIn = LocalDateTime.now();
        LocalDateTime checkOut = checkIn.plusDays(30);

        Booking booking = new Booking(
                TEST_ID,
                TEST_ROOM_NUMBER,
                TEST_EMAIL,
                TEST_NAME,
                TEST_PRICE,
                TEST_PHONE,
                TEST_MESSAGE,
                BookingStatus.CONFIRMED,
                checkIn,
                checkOut,
                TEST_CPF
        );

        assertNotNull(booking);
    }

    @Test
    void shouldAllowMinimumStayOf1Day() {
        LocalDateTime checkIn = LocalDateTime.now();
        LocalDateTime checkOut = checkIn.plusDays(1);

        Booking booking = new Booking(
                TEST_ID,
                TEST_ROOM_NUMBER,
                TEST_EMAIL,
                TEST_NAME,
                TEST_PRICE,
                TEST_PHONE,
                TEST_MESSAGE,
                BookingStatus.CONFIRMED,
                checkIn,
                checkOut,
                TEST_CPF
        );

        assertNotNull(booking);
    }

}