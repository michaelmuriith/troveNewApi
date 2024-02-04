package com.devkenya.user.dto.res;

import com.devkenya.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

public record DiscoverItems(
        int code,
        String msg,
        List<DiscoverItem> data
) {
    public static DiscoverItems fromUsers(int code, String msg, List<User> users) {
        List<DiscoverItem> items = users.stream()
                .map(user -> new DiscoverItem(
                        user.getToken(),
                        user.getFirstName() + " " + user.getLastName(),
                        user.getDescription(),
                        user.getAvatar(),
                        user.isOnline() ? 1 : 0,
                        user.getFcmToken()
                ))
                .toList();

        return new DiscoverItems(code, msg, List.copyOf(items));
    }
}
