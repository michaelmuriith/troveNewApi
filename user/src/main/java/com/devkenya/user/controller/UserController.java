package com.devkenya.user.controller;

import com.devkenya.user.dto.req.UserRegisterRequest;
import com.devkenya.user.dto.res.DiscoverItems;
import com.devkenya.user.dto.res.UserItem;
import com.devkenya.user.dto.res.UserRes;
import com.devkenya.user.model.User;
import com.devkenya.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    //register user
    @RequestMapping
    public UserRes registerUser(
            @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        try {
            log.info("Registering user: {}", userRegisterRequest);
            User user = userService.registerUser(userRegisterRequest);
            return new UserRes(
                    0,
                    "User registered successfully",
                    user

            );
        } catch (Exception e) {
            return new UserRes(
                    1,
                    e.getMessage(),
                    (User) null
            );
        }
    }

    //login user
    @RequestMapping("/login")
    public UserRes loginUser(
            @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        try {
            log.info("Logging in user: {}", userRegisterRequest);
            User user = userService.LoginUser(userRegisterRequest);
            return new UserRes(
                    0,
                    "User logged in successfully",
                    user
            );
        } catch (Exception e) {
            return new UserRes(
                    1,
                    e.getMessage(),
                    (User) null
            );
        }
    }


    //get user
    @RequestMapping("/get")
    public User getUser(
            @RequestBody String id
    ) {
        log.info("Getting user: {}", id);
        return userService.getUser(id);
    }

    //get users
    @RequestMapping("/get/all")
    public DiscoverItems getUsers() {
        try {
            log.info("Getting users");

            List<User> users = userService.getUsers();
            return DiscoverItems.fromUsers(0, "Users retrieved successfully", users);
        } catch (Exception e) {
            log.error("Error while retrieving users", e);
            return new DiscoverItems(1, "Error retrieving users", List.of());
        }
    }


}
