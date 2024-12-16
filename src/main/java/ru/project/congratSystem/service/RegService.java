package ru.project.congratSystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.UsersRepo;

@Service
public class RegService {
    private final UsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;


    public RegService(UsersRepo usersRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signIn(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepo.save(user);
    }
}
