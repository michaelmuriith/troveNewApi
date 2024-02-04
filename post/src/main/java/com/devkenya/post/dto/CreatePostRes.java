package com.devkenya.post.dto;

import com.devkenya.post.dto.req.CreatePostReq;
import com.devkenya.post.model.Post;

public record CreatePostRes(
        int code,
        String message,
        Post post
) {
    public static CreatePostRes from(CreatePostReq req, Post post) {
        return new CreatePostRes(200, "Post created successfully", post);
    }
}
