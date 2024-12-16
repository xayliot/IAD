package ru.project.congratSystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.UsersRepo;
import ru.project.congratSystem.service.RegService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegServiceTest {

    @Mock
    private UsersRepo usersRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegService regService;

    @Test
    void testSignIn() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(passwordEncoder.encode("testPassword")).thenReturn("encodedPassword");

        // Act
        regService.signIn(user);

        // Assert
        verify(passwordEncoder).encode("testPassword");
        verify(usersRepo).save(user);
    }
}
