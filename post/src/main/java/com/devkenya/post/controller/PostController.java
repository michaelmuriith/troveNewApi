package com.devkenya.post.controller;

import com.devkenya.post.dto.req.CreatePostReq;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class PostController {
    @RequestMapping("/post")
    public void createPost(
            @RequestBody CreatePostReq createPostReq
            ) {
        log.info("Creating post {}", createPostReq);
    }
}
