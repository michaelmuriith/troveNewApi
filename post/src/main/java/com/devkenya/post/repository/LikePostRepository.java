package com.devkenya.post.repository;

import com.devkenya.post.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface LikePostRepository extends JpaRepository<LikePost, String>{

    boolean existsByPostIdAndUserId(UUID postId, UUID userId);

    void deleteByPostIdAndUserId(UUID uuid, UUID uuid1);
}
