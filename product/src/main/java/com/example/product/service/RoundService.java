package com.example.product.service;

import com.example.product.entity.Round;
import com.example.product.graphql.dto.RoundOutput;
import com.example.product.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.product.graphql.dto.RoundOutput.convertToRoundOutput;

@RequiredArgsConstructor
@Service
public class RoundService {
    private final RoundRepository roundRepository;

    public RoundOutput getRoundByProductId(Long productId) {
        Round round = roundRepository.findByProductProductId(productId);
        return convertToRoundOutput(round);
    }

}
