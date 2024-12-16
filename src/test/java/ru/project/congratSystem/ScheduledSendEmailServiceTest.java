package ru.project.congratSystem;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import ru.project.congratSystem.service.ScheduledSendEmailService;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduledSendEmailServiceTest {

    @Mock
    private JavaMailSender mailSender;
    @InjectMocks
    private ScheduledSendEmailService scheduledSendEmailService;

    @Test
    public void testSendScheduledMessage() throws InterruptedException {
        // Arrange (подготовка данных для теста)
        String receiptEmailAddress = "test@example.com";
        String titleOfMessage = "Test Subject";
        String textOfMessage = "Test Body";
        Long date = System.currentTimeMillis() + 1000;

        when(mailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act
        scheduledSendEmailService.sendScheduledMessage(receiptEmailAddress, titleOfMessage, textOfMessage, date);

        // Assert
        verify(mailSender).createMimeMessage();
    }

}
