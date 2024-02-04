package com.devkenya.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(
        scanBasePackages = {
                "com.devkenya.user",
                "com.devkenya.msgQs",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.devkenya.clients"
)
public class  UserApplication {

        public static void main(String[] args) {
            SpringApplication.run(UserApplication.class, args);
        }
}
