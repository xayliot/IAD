package ru.project.congratSystem.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.project.congratSystem.model.Congratulation;
import ru.project.congratSystem.model.Friend;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.CongratsRepo;
import ru.project.congratSystem.repository.FriendsRepo;
import ru.project.congratSystem.repository.UsersRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CongratService {
    private final UsersRepo usersRepo;
    private final CongratsRepo congratsRepo;
    private final FriendsRepo friendsRepo;

    @Autowired
    public CongratService(UsersRepo usersRepo, CongratsRepo congratsRepo, FriendsRepo friendsRepo) {
        this.usersRepo = usersRepo;
        this.congratsRepo = congratsRepo;
        this.friendsRepo = friendsRepo;
    }

    @Transactional
    public void addCongrat(Long frId , String title, String text, String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String ownerUsername = authentication.getName();

        Optional<User> userOptional = usersRepo.findByUsername(ownerUsername);
        User owner = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Friend> friendOptional = friendsRepo.findFriendById(frId);
        Friend friendToCongrat = friendOptional.orElseThrow(() -> new RuntimeException("Friend not found"));




        Congratulation congratulation = new Congratulation();
        congratulation.setTitle(title);
        congratulation.setText(text);
        congratulation.setWhoCongr(friendToCongrat);
        congratulation.setMailAdress(email);
        congratulation.setOwnerOfCongrat(owner);
        congratsRepo.save(congratulation);
    }

    @Transactional
    public List<Congratulation> showAllCongrats(User user){

        return congratsRepo.findByOwnerOfCongrat(user);
    }
}
