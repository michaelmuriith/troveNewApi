package com.devkenya.post.dto;

import com.devkenya.clients.user.UserResponse;

import java.util.UUID;

public record PostsResItem(
        UUID id,
        UserResponse user,
        String content,
        boolean hasImage,
        String image,
        boolean hasVideo,
        String video,
        boolean hasGif,
        String gif,
        boolean hasLiked,
        String createdAt

) {
    public PostsResItem(UUID id, UserResponse userResponse, String content, boolean hasImage, boolean b, boolean hasVideo, boolean hasLiked, String string) {
        this(id, userResponse, content, hasImage, null, hasVideo, null, false, null, hasLiked, string);
    }
}
