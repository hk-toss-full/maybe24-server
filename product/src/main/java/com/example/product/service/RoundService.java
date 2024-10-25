package com.example.product.service;

import com.example.product.entity.Round;
import com.example.product.graphql.dto.RoundOutput;
import com.example.product.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoundService {
    private final RoundRepository roundRepository;

    public RoundOutput getRoundByProductId(Long productId) {
        Round round = roundRepository.findByProductProductId(productId);
        return convertToRoundOutput(round);
    }

    private RoundOutput convertToRoundOutput(Round round){
        return new RoundOutput(
                round.getRoundId(),
                round.getDate(),
                round.getPrice(),
                round.getTotalCnt(),
                round.getProduct().getProductId()
        );
    }
}
