package com.example.user.account.service;

import com.example.user.account.dto.TransactionRequest;
import com.example.user.account.entity.Account;

public interface AccountService {
    Account getMyAccount(String userId);
    Account deposit(String userId, TransactionRequest request);
    Account withdrawal(String userId, TransactionRequest request);
//    List<Pay> getAllPayments(UserResponse user);
}
