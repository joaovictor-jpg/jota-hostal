package br.com.jota.payment.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static br.com.jota.payment.entity.PaymentStatus.PENDING;

@Table(name = "payments")
@Entity(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "id_booking")
    private UUID idBooking;
    @Column(name = "guest_name")
    private String guestName;
    @Column(name = "guest_email")
    private String guestEmail;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_validity")
    private LocalDate cardValidity;
    @Column(name = "payment_value")
    private BigDecimal paymentValue;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;
    @Column(name = "guest_cpf")
    private String guestCpf;
    @Column(name = "card_cvv")
    private String cardCVV;
    private String country;
    private String telephone;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Payment() {
    }

    public Payment(UUID idBooking, String guestName, String guestEmail, BigDecimal paymentValue, String telephone, String guestCpf) {
        this.idBooking = idBooking;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.paymentValue = paymentValue;
        this.telephone = telephone;
        this.guestCpf = guestCpf;
        this.status = PENDING;
    }

    public Long getId() {
        return Id;
    }

    public UUID getIdBooking() {
        return idBooking;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getCardValidity() {
        return cardValidity;
    }

    public BigDecimal getPaymentValue() {
        return paymentValue;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getGuestCpf() {
        return guestCpf;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public String getCountry() {
        return country;
    }

    public String getTelephone() {
        return telephone;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreateAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(Id, payment.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}
