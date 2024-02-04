package com.devkenya.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient("fraud")
public interface FraudClient {
    @PostMapping(path = "api/v1/fraud/{customerId}")
    FraudCheckResponse checkFraud(@PathVariable("customerId") UUID id);
}
