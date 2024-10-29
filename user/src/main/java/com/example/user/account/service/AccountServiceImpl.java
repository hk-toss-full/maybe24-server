package com.example.user.account.service;

import com.example.user.account.dto.TransactionRequest;
import com.example.user.account.entity.Account;
import com.example.user.account.repository.AccountRepository;
import com.example.user.global.CustomException;
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
        Optional<Account> account = accountRepository.findByUser(user);
        if (account.isEmpty()) throw new CustomException(404, "존재하지 않는 계좌입니다");
        return account.get();
    }

    @Override
    public Account deposit(String accountId, TransactionRequest request){
        Account account = getMyAccount(accountId);

        account.changeBalance(request.amount());
        accountRepository.save(account);
        return account;
    };

    @Override
    public Account withdrawal(String userId, TransactionRequest request){
        Account account = getMyAccount(userId);
        if(account.getBalance() < request.amount()) throw new CustomException(400,"잔액이 부족합니다");
        account.changeBalance(-request.amount());
        accountRepository.save(account);
        return account;
    };
//    List<Pay> getAllPayments(){};
}
