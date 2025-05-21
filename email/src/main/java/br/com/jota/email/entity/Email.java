package br.com.jota.email.entity;

import br.com.jota.email.dto.CreatedEmail;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "email")
public class Email {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String email;
    private Integer roomNumber;
    private String message;
    private String text;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    @CreatedDate
    private LocalDateTime createdAt;

    public Email() {
    }

    public Email(String name, String email, Integer roomNumber, String message, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.name = name;
        this.email = email;
        this.roomNumber = roomNumber;
        this.message = message;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public void sendEmail(CreatedEmail email) {
        this.name = email.nameGuest();
        this.email = email.email();
        this.roomNumber = email.roomNumber();
        this.message = email.title();
        this.text = email.text();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getMessage() {
        return message;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
