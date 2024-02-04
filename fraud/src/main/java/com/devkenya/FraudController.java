package com.devkenya;

import com.devkenya.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/fraud")
@AllArgsConstructor
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @PostMapping(path = "{customerId}")
    public FraudCheckResponse checkFraud(
            @PathVariable("customerId") UUID id
    ) {
        boolean isFraudulent = fraudCheckService.isFraudulent(id);
        return new FraudCheckResponse(isFraudulent);
    }
}
