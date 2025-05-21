package br.com.jota.email.validation;

import br.com.jota.email.dto.CreatedEmail;
import br.com.jota.email.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator {
    public static void validate(CreatedEmail createdEmail) {
        System.out.println(createdEmail.roomNumber());
        validateEmail(createdEmail.email());
        validateRoomNumber(createdEmail.roomNumber());
        validateNameGuest(createdEmail.nameGuest());
        validateTitle(createdEmail.title());
        validateText(createdEmail.text());
    }

    private static void validateEmail(String email) throws ValidationException {
        if (email == null || email.isBlank()) {
            throw new ValidationException("O email não pode estar em branco");
        }

        if (email.length() > 100) {
            throw new ValidationException("O email não pode ter mais que 100 caracteres");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("Deve ser um endereço de email válido");
        }
    }

    private static void validateRoomNumber(Integer roomNumber) throws ValidationException {
        if (roomNumber == null) {
            throw new ValidationException("O número do quarto não pode ser nulo");
        }

        if (roomNumber <= 0) {
            throw new ValidationException("O número do quarto deve ser positivo");
        }
    }

    private static void validateNameGuest(String name) throws ValidationException {
        if (name == null || name.isBlank()) {
            throw new ValidationException("O nome do hóspede não pode estar em branco");
        }

        if (name.length() < 2 || name.length() > 50) {
            throw new ValidationException("O nome deve ter entre 2 e 50 caracteres");
        }
    }

    private static void validateTitle(String title) throws ValidationException {
        if (title == null || title.isBlank()) {
            throw new ValidationException("O título não pode estar em branco");
        }

        if (title.length() < 5 || title.length() > 100) {
            throw new ValidationException("O título deve ter entre 5 e 100 caracteres");
        }
    }

    private static void validateText(String text) throws ValidationException {
        if (text == null || text.isBlank()) {
            throw new ValidationException("O texto não pode estar em branco");
        }

        if (text.length() < 10 || text.length() > 2000) {
            throw new ValidationException("O texto deve ter entre 10 e 2000 caracteres");
        }
    }
}
