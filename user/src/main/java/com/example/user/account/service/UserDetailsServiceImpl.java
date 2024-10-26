package com.example.user.account.service;

import com.example.user.user.entity.User;
import com.example.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByNickname(username).orElseThrow();
    }

    public User loadUserByUserId(String userId)  {
        return userRepository.findUserByUserId(userId).orElseThrow();
        }
}
