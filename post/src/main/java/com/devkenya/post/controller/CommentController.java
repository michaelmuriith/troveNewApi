package com.devkenya.post.controller;

import com.devkenya.post.dto.req.CommentReq;
import com.devkenya.post.dto.res.CommentRes;
import com.devkenya.post.model.Comment;
import com.devkenya.post.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/posts")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public Comment createComment(
            @PathVariable("postId") String postId,
            @RequestParam("userId") String userId,
            @RequestBody CommentReq commentReq
    ) {
        try {
            log.info("Creating comment: {}", commentReq);
            // Call comment service to create a comment
            Comment comment = commentService.createComment(commentReq, userId, postId);
            if (comment == null) {
                log.error("Error creating comment");
                return null;
            }
            return comment;
        } catch (Exception e) {
            log.error("Error creating comment", e);
            return null;
        }
    }

    //get all comments
    @GetMapping("/{postId}/comments")
    public Page<CommentRes> getComments(
            @PathVariable("postId") String postId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        try {
            log.info("Getting comments for post: {}", postId);
            // Call comment service to get all comments
            return commentService.getComments(postId, page, size);
        } catch (Exception e) {
            log.error("Error getting comments", e);
            return null;
        }
    }
}
