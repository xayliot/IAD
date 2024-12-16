package ru.project.congratSystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.project.congratSystem.model.Friend;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.FriendsRepo;
import ru.project.congratSystem.repository.UsersRepo;
import ru.project.congratSystem.service.FriendService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendServiceTest {

    @Mock
    private UsersRepo usersRepo;

    @Mock
    private FriendsRepo friendsRepo;

    @InjectMocks
    private Friend friend;

    @InjectMocks
    private FriendService friendService;

    @Test
    void testAddFriend() {
        // Arrange
        Authentication authentication = new UsernamePasswordAuthenticationToken("testUser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);


        User owner = new User();
        owner.setUsername("testUser");

        when(usersRepo.findByUsername("testUser")).thenReturn(Optional.of(owner));

        // Act
        friendService.addFriend(friend);

        // Assert
        verify(friendsRepo).save(friend);
    }

    @Test
    void testShowMyFriends() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");

        when(friendsRepo.findFriendByOwner(user)).thenReturn(List.of(new Friend())); // Пример заполнения списка

        // Act
        friendService.showMyFriends(user);

        // Assert
        verify(friendsRepo).findFriendByOwner(user);
        // Добавьте дополнительные проверки, основанные на вашей логике
    }
}
