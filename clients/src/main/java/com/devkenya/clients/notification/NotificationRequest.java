package com.devkenya.clients.notification;

import java.util.UUID;

public record NotificationRequest(
        UUID toUserId,
        String toUserEmail,
        String toUserPhone,
        String message,
        String subject,
        NotificationType type,
        String sender
) {
}
