package ru.project.congratSystem.service;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.UsersRepo;
import ru.project.congratSystem.security.UsersDetails;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepo usersRepo;

    public UsersDetailsService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepo.findByUsername(username);
        if(user.isEmpty()){
            System.out.println("ОБОСРАМС");
            throw new UsernameNotFoundException("User not found");
        }
        else {
            return new UsersDetails(user.get());
        }


    }
}
