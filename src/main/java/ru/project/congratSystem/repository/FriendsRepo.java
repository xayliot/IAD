package ru.project.congratSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.congratSystem.model.Friend;
import ru.project.congratSystem.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendsRepo extends JpaRepository<Friend, Long> {

    Optional<Friend> findFriendByFriendName(String friendName);

    List<Friend> findFriendByOwner(User owner);
    Optional<Friend> findFriendById(Long id);



}
