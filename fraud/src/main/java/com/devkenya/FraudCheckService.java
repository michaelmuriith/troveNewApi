package com.devkenya;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulent(UUID userId) {
        log.info("Checking fraud for user with id: {}", userId);
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
                .userId(userId)
                .isFraud(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());

        return false;
    }


}
