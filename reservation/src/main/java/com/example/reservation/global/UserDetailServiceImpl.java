package com.example.reservation.global;



import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserApi userApi; // main 에 @EnableFeignClients 추가
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userApi.me(username);
    }
}

