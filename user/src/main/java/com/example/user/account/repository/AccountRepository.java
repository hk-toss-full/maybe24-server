package com.example.user.account.repository;

import com.example.user.account.entity.Account;
import com.example.user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUser(User user);
    Optional<Account> findById(Long id);

}
