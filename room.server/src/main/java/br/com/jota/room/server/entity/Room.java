package br.com.jota.room.server.entity;

import br.com.jota.room.server.dto.CreatedRoom;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Room")
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idRoom;
    @Column(name = "area_m2")
    private BigDecimal areaM2;
    @Column(name = "room_number")
    private Integer roomNumber;
    @Column(name = "rental_value")
    private BigDecimal rentalValue;
    @Column(name = "condo_fee")
    private BigDecimal condoFee;
    @Column(name = "accepts_animals")
    private Boolean acceptsAnimals;
    private String address;
    private String description;
    private String telephone;
    @Column(name = "room_status")
    private Status status;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Room() {
    }

    public Room(CreatedRoom createdRoom) {
        this.areaM2 = createdRoom.areaM2();
        this.roomNumber = createdRoom.roomNumber();
        this.rentalValue = createdRoom.rentalValue();
        this.condoFee = createdRoom.condoFee();
        this.acceptsAnimals = createdRoom.acceptsAnimals();
        this.address = createdRoom.address();
        this.description = createdRoom.description();
        this.telephone = createdRoom.telephone();
        this.status = createdRoom.status();
    }

    public UUID getIdRoom() {
        return idRoom;
    }

    public BigDecimal getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(BigDecimal areaM2) {
        this.areaM2 = areaM2;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public BigDecimal getRentalValue() {
        return rentalValue;
    }

    public void setRentalValue(BigDecimal rentalValue) {
        this.rentalValue = rentalValue;
    }

    public BigDecimal getCondoFee() {
        return condoFee;
    }

    public void setCondoFee(BigDecimal condoFee) {
        this.condoFee = condoFee;
    }

    public Boolean getAcceptsAnimals() {
        return acceptsAnimals;
    }

    public void setAcceptsAnimals(Boolean acceptsAnimals) {
        this.acceptsAnimals = acceptsAnimals;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
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
        Room room = (Room) o;
        return Objects.equals(idRoom, room.idRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idRoom);
    }
}
