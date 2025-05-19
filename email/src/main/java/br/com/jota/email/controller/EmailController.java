package br.com.jota.email.controller;

import br.com.jota.email.dto.EmailResponse;
import br.com.jota.email.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<EmailResponse>> listEmail() {
        return ResponseEntity.ok().body(emailService.listEmail());
    }
}
