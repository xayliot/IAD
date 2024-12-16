package ru.project.congratSystem;

import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.project.congratSystem.controller.FriendsController;
import ru.project.congratSystem.model.Friend;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.UsersRepo;
import ru.project.congratSystem.service.FriendService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
@WebMvcTest(FriendsController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
public class FriendsControllerTest {

    @Mock
    private FriendService friendService;


    @Mock
    private UsersRepo usersRepo;

    @InjectMocks
    private FriendsController friendsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFriendSuccess() {
        // Arrange
        Mockito.doNothing().when(friendService).addFriend(any(Friend.class));

        // Act
        ResponseEntity<Map<String, String>> responseEntity = friendsController.addFriend(
                "John", "Doe", "1990-01-01", "john.doe@example.com");

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).containsEntry("message", "Друг успешно добавлен");
    }

    @Test
    void testAddFriendFailure() {
        // Arrange
        Mockito.doThrow(Exception.class).when(friendService).addFriend(any(Friend.class));

        // Act
        ResponseEntity<Map<String, String>> responseEntity = friendsController.addFriend(
                "John", "Doe", "1990-01-01", "john.doe@example.com");

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).containsEntry("message", "Не удалось добавить друга.");
    }

    @Test
    void testGetUserFriends() throws IOException {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        Mockito.when(usersRepo.findByUsername(anyString())).thenReturn(Optional.of(user));

        List<Friend> friends = Collections.singletonList(new Friend());
        Mockito.when(friendService.showMyFriends(any(User.class))).thenReturn(friends);

        // Act
        ResponseEntity<List<Friend>> responseEntity = friendsController.getUserFriends();

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(friends);
    }
}
