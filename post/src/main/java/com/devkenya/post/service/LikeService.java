package com.devkenya.post.service;

import com.devkenya.post.model.LikePost;
import com.devkenya.post.repository.LikePostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class LikeService {
    private final LikePostRepository likePostRepository;

    // Like a post
    public void likePost(String postId, String userId) {
        try {
            LikePost like = LikePost.builder()
                    .postId(UUID.fromString(postId))
                    .userId(UUID.fromString(userId))
                    .build();

            likePostRepository.saveAndFlush(like);

        } catch (Exception e) {
            log.error("Error liking post", e);
        }
    }

    // Unlike a post
    public void unlikePost(String postId, String userId) {
        try {
            likePostRepository.deleteByPostIdAndUserId(UUID.fromString(postId), UUID.fromString(userId));
        } catch (Exception e) {
            log.error("Error unliking post", e);
            throw e;
        }
    }

}
