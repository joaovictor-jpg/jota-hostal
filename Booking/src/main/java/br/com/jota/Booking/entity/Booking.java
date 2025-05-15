package br.com.jota.Booking.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Booking")
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "room_number")
    private Integer roomNumber;
    private String email;
    @Column(name = "name_guest")
    private String nameGuest;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    private String telephone;
    private String message;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @Column(name = "check_in")
    private LocalDateTime checkIn;
    @Column(name = "check_out")
    private LocalDateTime checkOut;
    @Column(name = "guest_cpf")
    private String guestCpf;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Booking() {
    }

    public Booking(Integer roomNumber, String email, String nameGuest, BigDecimal totalPrice, String telephone, String message, BookingStatus status, LocalDateTime checkIn, LocalDateTime checkOut, String guestCpf) {
        this.roomNumber = roomNumber;
        this.email = email;
        this.nameGuest = nameGuest;
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
        return nameGuest;
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

    public void setStatus(BookingStatus status) {
        this.status = status;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
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
}
