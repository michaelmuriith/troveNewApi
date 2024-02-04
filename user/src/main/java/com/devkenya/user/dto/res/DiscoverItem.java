package com.devkenya.user.dto.res;

import java.net.URI;
import java.net.URL;

public record DiscoverItem(
        String token,
        String name,
        String description,
        URL avatar,
        int online,
        String fcmToken
) {
}
