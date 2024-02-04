package com.devkenya.post.dto.res;

import com.devkenya.clients.user.UserResponse;

import java.net.URI;
import java.util.UUID;

public record CommentRes(
        UUID id,
        UserResponse user,
        String userId,
        String postId,
        String content,
        boolean hasImage,
        URI image,
        boolean hasVideo,
        URI video,
        boolean hasGif,
        URI gif
) {
}
