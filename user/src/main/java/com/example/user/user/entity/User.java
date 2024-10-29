package com.example.user.user.entity;

import com.example.user.account.entity.Account;
import com.example.user.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(
        name = "USER"
)
public class User extends BaseEntity implements UserDetails{
    @Id
    private String userId;
    @Column(nullable=false)
    private String nickname;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String phone;
    @Column(nullable=false)
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private final Account account = new Account(this);


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(()->"ROLE_USER");
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}