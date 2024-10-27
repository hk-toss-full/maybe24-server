package com.example.user.account.entity;

import com.example.user.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
        name = "ACCOUNT"
)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long balance;

    @OneToOne(mappedBy = "account")
    private User user;

    public Account(User user) {
        this.balance = 1_000_000L;
        this.user = user;
    }

    public void changeBalance(int amount) {
        this.balance += amount;
    };
}
