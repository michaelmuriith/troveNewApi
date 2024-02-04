package com.devkenya.clients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("user")
public interface UserServiceClient {

    @GetMapping("/api/v1/user/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId);

    //get users by a list of ids
    @GetMapping("/api/v1/user/getUsersByIds")
    List<UserResponse> getUsersByIds(@RequestBody List<String> userIds);
}
