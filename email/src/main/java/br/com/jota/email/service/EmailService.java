package br.com.jota.email.service;

import br.com.jota.email.dto.CreatedEmail;
import br.com.jota.email.dto.EmailResponse;
import br.com.jota.email.dto.SendEmail;
import br.com.jota.email.entity.Email;
import br.com.jota.email.exception.RegraDeNegorcioException;
import br.com.jota.email.repository.EmailRepository;
import br.com.jota.email.validation.EmailValidator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender sendEmail;
    private final EmailRepository emailRepository;
    private static final String EMAIL_ORIGEM = "jotahost@email.com";
    private static final String SENDER_NAME = "JotaHotel";

    public EmailService(JavaMailSender sendEmail, EmailRepository emailRepository) {
        this.sendEmail = sendEmail;
        this.emailRepository = emailRepository;
    }

    public void save(SendEmail sendEmail) {
        Email email = new Email(sendEmail.nameGuest(), sendEmail.email(), sendEmail.roomNumber(), sendEmail.mensagem(),
                sendEmail.checkIn(), sendEmail.checkOut());
        emailRepository.save(email);
        String text = this.messageEmail(email);
        sendEmail(email, text);
    }

    public void createEmail(CreatedEmail createdEmail) {
        Email email = new Email();
        EmailValidator.validate(createdEmail);
        email.sendEmail(createdEmail);
        emailRepository.save(email);
        sendEmail(email, createdEmail.text());
    }

    public List<EmailResponse> listEmail() {
        return emailRepository.findAll().stream().map(EmailResponse::new).toList();
    }

    private void sendEmail(Email email, String text) {
        MimeMessage message = sendEmail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(EMAIL_ORIGEM, SENDER_NAME);
            helper.setTo(email.getEmail());
            helper.setSubject(email.getEmail());
            helper.setText(text, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RegraDeNegorcioException("Erro ao enviar email");
        }

        sendEmail.send(message);
    }

    private String messageEmail(Email email) {
        String htmlText = String.format(
                """
                <html>
                  <body>
                    <p>Prezado(a) hóspede,</p>
                    
                    <p>Sua reserva foi confirmada com sucesso!</p>
                    
                    <h3>Detalhes da reserva:</h3>
                    <ul>
                      <li><strong>Número do quarto:</strong> %d</li>
                      <li><strong>Check-in:</strong> %s</li>
                      <li><strong>Check-out:</strong> %s</li>
                      <li><strong>Período total:</strong> %d noite(s)</li>
                    </ul>
                    
                    <p>Estamos ansiosos para recebê-lo(a) no nosso hotel!<br>
                    Qualquer dúvida ou necessidade especial, por favor entre em contato.</p>
                    
                    <p>Atenciosamente,<br>
                    <strong>Equipe Jota Hostel</strong></p>
                  </body>
                </html>
                """,
                email.getRoomNumber(),
                email.getCheckIn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                email.getCheckOut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                ChronoUnit.DAYS.between(email.getCheckIn(), email.getCheckOut())
        );

        return htmlText;
    }
}
