package com.example.user.user.service;

import com.example.user.global.JwtUtils;
import com.example.user.user.dto.LoginRequest;
import com.example.user.user.dto.RegisterRequest;
import com.example.user.user.entity.User;
import com.example.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.rmi.server.LogStream.log;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
        Optional<User> loginUser = userRepository.findByUserId(loginRequest.userId().toString());
        if(loginUser.isEmpty())
            throw new RuntimeException("해당 아이디를 가진 사용자가 없습니다");
        User user = loginUser.get();
        if(!passwordEncoder.matches(
                loginRequest.password(), user.getPassword()))
            throw new RuntimeException("아이디와 비밀번호가 일치하지 않습니다");
        return jwtUtils.generateToken(user.getUserId());
    }

    @Override
    public void register(RegisterRequest request) {
        Optional<User> byUserId = userRepository.findByUserId(request.userId());
        if(byUserId.isPresent()) throw new RuntimeException("이미 등록된 아이디입니다");
        User loggedInUser = request.toEntity(passwordEncoder);
        userRepository.save(loggedInUser);
    }

    @Override
    public Boolean checkDup(String id){
        Optional<User> byUserId = userRepository.findByUserId(id);
        log(byUserId+"  "+byUserId.isPresent());
        return byUserId.isEmpty();
    }
}
