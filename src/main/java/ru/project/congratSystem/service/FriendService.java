package ru.project.congratSystem.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.project.congratSystem.model.Friend;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.FriendsRepo;
import ru.project.congratSystem.repository.UsersRepo;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {

    private final UsersRepo usersRepo;
    private final FriendsRepo friendsRepo;


    @Autowired
    public FriendService(UsersRepo usersRepo, FriendsRepo friendsRepo) {
        this.usersRepo = usersRepo;
        this.friendsRepo = friendsRepo;
    }

    @Transactional
    public void addFriend(Friend friend){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String ownerUsername = authentication.getName();

        Optional<User> userOptional = usersRepo.findByUsername(ownerUsername);
        User owner = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        friend.setOwner(owner);
        friendsRepo.save(friend);

    }

    @Transactional
    public List<Friend> showMyFriends(User user){

        return friendsRepo.findFriendByOwner(user);
    }
}
