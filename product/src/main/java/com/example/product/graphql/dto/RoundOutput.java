package com.example.product.graphql.dto;

import com.example.product.entity.Round;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RoundOutput {
    private Long roundId;
    private Date date;
    private int price;
    private int totalCnt;
    private Long productId;

    public static RoundOutput convertToRoundOutput(Round round){
        return new RoundOutput(
                round.getRoundId(),
                round.getDate(),
                round.getPrice(),
                round.getTotalCnt(),
                round.getProduct().getProductId()
        );
    }
}
