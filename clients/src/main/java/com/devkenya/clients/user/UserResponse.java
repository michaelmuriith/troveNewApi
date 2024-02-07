package com.devkenya.clients.user;

import lombok.Getter;

import java.net.URL;
import java.util.UUID;

public record UserResponse(
        UUID id,

        URL businessLogo,
        URL avatar,

        String businessName,
        String firstName,
        String lastName,

        String phone,
        String email,
        String fcmToken,
        String token,
        String accessToke,
        String description,
        boolean isOrganisation,
        boolean isOnline,
        boolean isApproved,
        boolean isBlocked
) {
}
