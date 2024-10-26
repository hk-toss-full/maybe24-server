package com.example.user.global;

import com.example.user.account.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    //    private final UserDetailsServiceImpl userService;
    private final UserDetailsServiceImpl userService;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        System.out.println("JwtFilter) authorization: "+authorization);
        if(authorization != null && authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            try{
                String userId = jwtUtils.parseToken(token);
                System.out.println("JwtFilter) username "+userId);
                UserDetails userDetails = userService.loadUserByUserId(userId);
                System.out.println("UserDetails.username) "+userDetails.getUsername()+", authorities "+userDetails.getAuthorities());

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 추가: 인증 정보 확인용 로그
                System.out.println("Authenticated user: " + userId);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
