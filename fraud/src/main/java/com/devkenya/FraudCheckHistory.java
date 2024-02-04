package com.devkenya;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class FraudCheckHistory {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;
    private boolean isFraud;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
