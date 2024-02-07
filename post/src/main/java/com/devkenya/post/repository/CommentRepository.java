package com.devkenya.post.repository;

import com.devkenya.post.dto.res.CommentRes;
import com.devkenya.post.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
   Page<Comment> findAllByPostId(String postId, Pageable pageable);
}
