package com.devkenya.notification.rabbitMq;

import com.devkenya.clients.notification.NotificationRequest;
import com.devkenya.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void Consumer(NotificationRequest notificationRequest) {
        log.info("Consumed message from RabbitMQ: {}", notificationRequest);
        notificationService.sendNotification(notificationRequest);

    }
}
