package br.com.jota.Booking.domain.entity;

import br.com.jota.Booking.domain.enums.BookingStatus;
import br.com.jota.Booking.domain.excption.BusinessRuleException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class Booking {
    private UUID id;
    private Integer roomNumber;
    private String email;
    private String NameGuest;
    private BigDecimal totalPrice;
    private String telephone;
    private String message;
    private BookingStatus status;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String guestCpf;

    public Booking(UUID id, Integer roomNumber, String email, String nameGuest, BigDecimal totalPrice, String telephone,
                   String message, BookingStatus status, LocalDateTime checkIn, LocalDateTime checkOut, String guestCpf) {
        this.theEndDateMustBeAfterTheStartDate(checkIn, checkOut);
        this.reservationDayLimits(checkIn, checkOut);
        this.id = id;
        this.roomNumber = roomNumber;
        this.email = email;
        this.NameGuest = nameGuest;
        this.totalPrice = totalPrice;
        this.telephone = telephone;
        this.message = message;
        this.status = status;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestCpf = guestCpf;
    }

    public Booking(Integer roomNumber, String email, String nameGuest, BigDecimal totalPrice, String telephone, String message,
                   BookingStatus status, LocalDateTime checkIn, LocalDateTime checkOut, String guestCpf) {
        this.theEndDateMustBeAfterTheStartDate(checkIn, checkOut);
        this.reservationDayLimits(checkIn, checkOut);
        this.roomNumber = roomNumber;
        this.email = email;
        this.NameGuest = nameGuest;
        this.totalPrice = totalPrice;
        this.telephone = telephone;
        this.message = message;
        this.status = status;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestCpf = guestCpf;
    }

    public UUID getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getNameGuest() {
        return NameGuest;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMessage() {
        return message;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public String getGuestCpf() {
        return guestCpf;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private void reservationDayLimits(LocalDateTime checkIn, LocalDateTime checkOut) {
        long daysBetween = ChronoUnit.DAYS.between(
                checkIn,
                checkOut
        );

        if (daysBetween < 1) {
            throw new BusinessRuleException("A estadia mínima é de 1 dia");
        }

        if (daysBetween > 30) {
            throw new BusinessRuleException("A estadia máxima é de 30 dias");
        }
    }

    private void theEndDateMustBeAfterTheStartDate(LocalDateTime checkIn, LocalDateTime checkOut) {
        if (checkIn.isAfter(checkOut)) {
            throw new BusinessRuleException("A data de Check-out deve ser depois de check-in");
        }
    }
}
