package com.example.user.account.service;

import com.example.user.account.dto.TransactionRequest;
import com.example.user.account.entity.Account;
import com.example.user.account.repository.AccountRepository;
import com.example.user.user.entity.User;
import com.example.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public Account getMyAccount(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Optional<Account> account = accountRepository.findAccountByUser(user);
        if (account.isEmpty()) throw new RuntimeException("Account not found");
        return account.get();
    }

    @Override
    public Account deposit(String userId, TransactionRequest request){
        Account account = getMyAccount(userId);
        account.changeBalance(request.amount());
        return account;
    };

    @Override
    public Account withdrawal(String userId, TransactionRequest request){
        Account account = getMyAccount(userId);
        if(account.getBalance() < request.amount()) throw new RuntimeException("Account balance not enough");
        account.changeBalance(-request.amount());
        return account;
    };
//    List<Pay> getAllPayments(){};
}
