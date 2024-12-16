package ru.project.congratSystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.project.congratSystem.model.Congratulation;
import ru.project.congratSystem.model.Friend;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.CongratsRepo;
import ru.project.congratSystem.repository.FriendsRepo;
import ru.project.congratSystem.repository.UsersRepo;
import ru.project.congratSystem.service.CongratService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CongratServiceTest {

    @Mock
    private UsersRepo usersRepo;

    @Mock
    private CongratsRepo congratsRepo;

    @Mock
    private FriendsRepo friendsRepo;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CongratService congratService;

    @Test
    void testAddCongrat() {
        // Arrange
        String ownerUsername = "testUser";
        when(authentication.getName()).thenReturn(ownerUsername);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User owner = new User();
        owner.setUsername(ownerUsername);
        when(usersRepo.findByUsername(ownerUsername)).thenReturn(Optional.of(owner));

        Long friendId = 1L;
        Friend friendToCongrat = new Friend();
        when(friendsRepo.findFriendById(friendId)).thenReturn(Optional.of(friendToCongrat));

        String title = "Test Title";
        String text = "Test Text";
        String email = "test@example.com";

        // Act
        congratService.addCongrat(friendId, title, text, email);

        // Assert
        verify(congratsRepo).save(any(Congratulation.class));
    }

    @Test
    void testShowAllCongrats() {
        // Arrange
        User user = new User();
        when(congratsRepo.findByOwnerOfCongrat(user)).thenReturn(Collections.emptyList());

        // Act
        congratService.showAllCongrats(user);

        // Assert
        verify(congratsRepo).findByOwnerOfCongrat(user);
    }
}
