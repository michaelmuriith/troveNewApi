package com.devkenya.notification;

import com.devkenya.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
        log.info("Sending notification: {}", notificationRequest);
        Notification notification = Notification.builder()
                .toUserId(notificationRequest.toUserId())
                .toUserEmail(notificationRequest.toUserEmail())
                .toUserPhone(notificationRequest.toUserPhone())
                .message(notificationRequest.message())
                .subject(notificationRequest.subject())
                .type(notificationRequest.type())
                .sender(notificationRequest.sender())
                .isSent(false)
                .isRead(false)
                .isDeleted(false)
                .isArchived(false)
                .sentAt(LocalDateTime.now())
                .readAt(null)
                .build();

        notificationRepository.saveAndFlush(notification);

        // todo: send notification

        log.info("Notification sent successfully");
    }
}
