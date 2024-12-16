package ru.project.congratSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.project.congratSystem.model.Friend;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.UsersRepo;
import ru.project.congratSystem.service.FriendService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {


    private final FriendService friendService;

    private final UsersRepo usersRepo;

    @Autowired
    public FriendsController(FriendService friendService, UsersRepo usersRepo) {
        this.friendService = friendService;
        this.usersRepo = usersRepo;
    }


    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addFriend(
            @RequestParam String friend_name,
            @RequestParam String friend_surname,
            @RequestParam String friend_date_of_birth,
            @RequestParam String friend_email) {
        try {
            Friend friend = new Friend();
            friend.setFriendName(friend_name);
            friend.setFriendSurname(friend_surname);
            friend.setFriendDateOfBirth(friend_date_of_birth);
            friend.setFriendEmail(friend_email);

            friendService.addFriend(friend);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Друг успешно добавлен");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Не удалось добавить друга.");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/showFriends")
    public ResponseEntity<List<Friend>> getUserFriends() throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Получить имя пользователя из UserDetails
        Optional<User> userOptional = usersRepo.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();
        List<Friend> friends = friendService.showMyFriends(user);
        return new ResponseEntity<>(friends, HttpStatus.OK);

    }
}







