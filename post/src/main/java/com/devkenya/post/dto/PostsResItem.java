package com.devkenya.post.dto;

import com.devkenya.clients.user.UserResponse;

import java.net.URI;
import java.util.UUID;

public record PostsResItem(
        UUID id,
        UserResponse user,
        String content,
        boolean hasImage,
        URI image,
        boolean hasVideo,
        URI video,
        boolean hasGif,
        URI gif,
        boolean hasLiked,
        String createdAt

) {

    public PostsResItem(UUID id, UserResponse userResponse, String content, boolean hasImage, URI image, boolean hasVideo, URI video, boolean hasGif, URI gif, Boolean orDefault, String string, Long orDefault1) {
        this(id, userResponse, content, hasImage, image, hasVideo, video, hasGif, gif, orDefault, string);
    }
}
