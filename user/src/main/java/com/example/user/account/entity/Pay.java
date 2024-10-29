package com.example.user.account.entity;

import com.example.user.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Pay extends BaseEntity {
    private Long payId;
    private Integer price;

    @ManyToOne
    private Account account;
}
