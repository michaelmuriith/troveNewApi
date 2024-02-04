package com.devkenya.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(
        scanBasePackages = {
                "com.devkenya.notification",
                "com.devkenya.msgQs",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.devkenya.clients"
)
public class  NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}