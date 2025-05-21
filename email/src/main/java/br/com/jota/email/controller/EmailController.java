package br.com.jota.email.controller;

import br.com.jota.email.dto.CreatedEmail;
import br.com.jota.email.dto.EmailResponse;
import br.com.jota.email.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody CreatedEmail createdEmail) {
        emailService.createEmail(createdEmail);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmailResponse>> listEmail() {
        return ResponseEntity.ok().body(emailService.listEmail());
    }
}
