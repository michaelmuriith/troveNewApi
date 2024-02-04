package com.devkenya.user.dto.res;

public record UserItem(
        String access_token,
        String token,
        String fcm_token,
        String name,
        String description,
        java.net.URL avatar,
        int online,
        int type
) {
}
