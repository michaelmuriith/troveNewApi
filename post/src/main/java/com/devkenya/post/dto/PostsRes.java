package com.devkenya.post.dto;

import org.springframework.data.domain.Page;

public record PostsRes(
        int code,
        String message,
        Page<PostsResItem> posts,
        int totalPages,
        long totalElements
) { }
