package br.com.jota.email.service;

import br.com.jota.email.dto.SendEmail;
import br.com.jota.email.entity.Email;
import br.com.jota.email.exception.RegraDeNegorcioException;
import br.com.jota.email.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    public void save(SendEmail email) {
        emailRepository.save(new Email(email.nameGuest(), email.email(), email.roomNumber(), email.mensagem(),
                email.checkIn(), email.checkOut()));
        sendEmail(email);
    }

    private void sendEmail(SendEmail email) {
        MimeMessage message = sendEmail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String text = this.messageEmail(email);

        try {
            helper.setFrom(EMAIL_ORIGEM, SENDER_NAME);
            helper.setTo(email.email());
            helper.setSubject(email.mensagem());
            helper.setText(text, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RegraDeNegorcioException("Erro ao enviar email");
        }

        sendEmail.send(message);
    }

    private String messageEmail(SendEmail email) {
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
                email.roomNumber(),
                email.checkIn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                email.checkOut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                ChronoUnit.DAYS.between(email.checkIn(), email.checkOut())
        );

        return htmlText;
    }
}
