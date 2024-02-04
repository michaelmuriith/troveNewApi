package com.devkenya.notification;

import com.devkenya.clients.notification.NotificationType;
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
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue
    private UUID notificationId;

    private UUID toUserId;
    private String toUserEmail;
    private String toUserPhone;
    private String message;
    private String subject;
    private NotificationType type;
    private String sender;
    private boolean isSent;
    private boolean isRead;
    private boolean isDeleted;
    private boolean isArchived;
    private LocalDateTime sentAt;
    private LocalDateTime readAt;
}
