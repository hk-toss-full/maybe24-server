package com.example.user.user.repository;

import com.example.user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUserId(String userId);
    Optional<User> findUserByNickname(String nickname);
}
