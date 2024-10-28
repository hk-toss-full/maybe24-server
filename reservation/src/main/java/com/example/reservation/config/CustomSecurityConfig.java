package com.example.reservation.config;

import com.example.reservation.global.UserDetailServiceImpl;
import com.example.reservation.global.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class CustomSecurityConfig {
    private final UserDetailServiceImpl userService;
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.userDetailsService(userService);
        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests(
                request -> request
                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/auth/check-dup",
                                "/api/v1/reserve",
                                "/api/v1/reserve/{reservationId}/confirm",
                                "/error"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );
        return http.build();
    }
}
