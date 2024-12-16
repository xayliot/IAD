package ru.project.congratSystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import ru.project.congratSystem.service.EmailSendService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@ExtendWith(MockitoExtension.class)
@PrepareForTest(EmailSendService.class)
public class EmailSendServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MimeMessage mimeMessage;

    @Mock
    private MimeMessageHelper mimeMessageHelper;

    @InjectMocks
    private EmailSendService emailSendService;

    @Test
    void testSendMessage() throws MessagingException, Exception {
        // Arrange
        String receiptAddress = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Body";

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        whenNew(MimeMessageHelper.class).withArguments(mimeMessage, true).thenReturn(mimeMessageHelper);

        // Act
        emailSendService.sendMessage(receiptAddress, subject, text);

        // Assert
        verify(mimeMessageHelper).setTo(receiptAddress);
        verify(mimeMessageHelper).setSubject(subject);
        verify(mimeMessageHelper).setText(text, true);
        verify(mailSender).send(mimeMessage);
    }
}
