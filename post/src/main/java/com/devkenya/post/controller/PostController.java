package com.devkenya.post.controller;

import com.devkenya.post.dto.PostsResItem;
import com.devkenya.post.dto.req.CreatePostReq;
import com.devkenya.post.model.Post;
import com.devkenya.post.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public Post createPost(
            @RequestBody CreatePostReq createPostReq
    ) {
        try {
            log.info("Creating post: {}", createPostReq);
            // Call post service to create a post
            Post post = postService.createPost(createPostReq);
            if (post == null) {
                log.error("Error creating post");
                return null;
            }
            return post;
        } catch (Exception e) {
            log.error("Error creating post", e);
            return null;
        }

    }

    // get all posts
    @GetMapping
    public Page<PostsResItem> getPosts(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "currentUser") String currentUser
    ) {
        try {
            log.info("Getting posts");
            // Call post service to get all posts
            return postService.getPosts(page, size, currentUser);
        } catch (Exception e) {
            log.error("Error getting posts", e);
            return null;
        }
    }
}
