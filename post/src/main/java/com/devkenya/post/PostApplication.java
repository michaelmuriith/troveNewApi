package com.devkenya.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.devkenya.post",
                "com.devkenya.user",
                "com.devkenya.msgQs",
        }
)
@EnableEurekaClient
@EnableFeignClients
public class PostApplication {

        public static void main(String[] args) {
            SpringApplication.run(PostApplication.class, args);
        }
}
