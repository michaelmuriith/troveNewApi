package com.devkenya.post.dto.req;

import java.net.URI;

public record CreatePostReq(
            String content,
            URI image,
            String userId
) {
}
