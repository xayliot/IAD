package ru.project.congratSystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.UsersRepo;
import ru.project.congratSystem.service.UsersDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersDetailsServiceTest {

    @Mock
    private UsersRepo usersRepo;

    @InjectMocks
    private UsersDetailsService usersDetailsService;

    @Test
    void testLoadUserByUsername() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        user.setPassword("testPassword");

        when(usersRepo.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = usersDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        verify(usersRepo).findByUsername(username);
    }

    @Test
    void testLoadUserByUsernameUserNotFound() {
        // Arrange
        String username = "nonExistingUser";

        when(usersRepo.findByUsername(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> usersDetailsService.loadUserByUsername(username));
        verify(usersRepo).findByUsername(username);
    }
}
