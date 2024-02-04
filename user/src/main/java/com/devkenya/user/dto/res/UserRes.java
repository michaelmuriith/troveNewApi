package com.devkenya.user.dto.res;

import com.devkenya.user.model.User;

public record UserRes(
        int code,
        String msg,
        UserItem data
) {
    // Additional constructor to convert LoginType to int
    public UserRes(int code, String msg, User user) {
        this(code, msg, new UserItem(
                user.getAccessToken(),
                user.getId(),
                user.getFcmToken(),
                user.getFirstName() + " " + user.getLastName(),
                user.getDescription(),
                user.getAvatar(),
                user.isOnline() ? 1 : 0,  // Convert boolean to int
                user.getType().getValue()  // Assuming getType() returns an enum
        ));
    }
}
