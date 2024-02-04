package com.devkenya.post.dto.req;

import java.net.URI;

public record CommentReq(
        String content,
        URI image,
        URI video,
        URI gif
) {
}
