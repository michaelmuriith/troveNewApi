package com.devkenya.post.service;

import com.devkenya.clients.user.UserResponse;
import com.devkenya.clients.user.UserServiceClient;
import com.devkenya.post.dto.req.CommentReq;
import com.devkenya.post.dto.res.CommentRes;
import com.devkenya.post.model.Comment;
import com.devkenya.post.repository.CommentRepository;
import com.devkenya.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CommentService {
    private final PostRepository postRepository;
    private final UserServiceClient userServiceClient;
    private final CommentRepository commentRepository;

    // create a comment
    public Comment createComment(CommentReq createCommentReq, String userId, String postId) {
        try {
            //check if post exists
            if (!postRepository.existsById(UUID.fromString(postId))) {
                log.error("Post with id {} not found. Unable to create comment.", postId);
                return null;
            }

            // Call user service to get user details and check if user exists using Feign client
            UserResponse userResponse = userServiceClient.getUserById(userId);
            log.info("User response: {}", userResponse);
            if (userResponse == null || userResponse.id() == null) {
                log.error("User with id {} not found. Unable to create comment.", userId);
                return null;
            }

            // create a comment
            Comment comment = Comment.builder()
                    .userId(userId)
                    .postId(postId)
                    .hasImage(createCommentReq.image() != null)
                    .image(createCommentReq.image())
                    .hasVideo(createCommentReq.video() != null)
                    .video(createCommentReq.video())
                    .hasGif(createCommentReq.gif() != null)
                    .gif(createCommentReq.gif())
                    .content(createCommentReq.content())
                    .build();

            log.info("Creating comment: {}", comment);

            return commentRepository.save(comment);

        } catch (Exception e) {
            log.error("Error creating comment", e);
            return null;
        }
    }

    // get all comments
    public Page<CommentRes> getComments(String postId, int page, int size) {
        try {
            // step 1: get all comments
            Page<Comment> commentsPage = commentRepository.findAllByPostId(postId, PageRequest.of(page - 1, size));
            log.info("Comments: {}", commentsPage);

            // step 2: Retrieve user details from the user service using Feign client in batches
            List<String> userIds = commentsPage.getContent().stream()
                    .map(Comment::getUserId)
                    .collect(Collectors.toList());
            log.info("User ids: {}", userIds);

            Map<UUID, UserResponse> userDetailsMap = fetchUserDetailsInBatches(userIds);
            log.info("User details map: {}", userDetailsMap);

            // step 3: map comments to comment response
            List<CommentRes> commentResItems = commentsPage.getContent().parallelStream()
                    .map(comment -> createCommentResItem(comment, userDetailsMap))
                    .toList();

            // step 4: return the response
            return new PageImpl<>(commentResItems, commentsPage.getPageable(), commentsPage.getTotalElements());

        } catch (Exception e) {
            log.error("Error getting comments", e);
            return null;
        }
    }

    private CommentRes createCommentResItem(Comment comment, Map<UUID, UserResponse> userDetailsMap) {
        UserResponse userResponse = userDetailsMap.get(comment.getUserId());
        log.info("User response: {}", userResponse);
        return new CommentRes(
                comment.getId(),
                userResponse,
                comment.getUserId(),
                comment.getPostId(),
                comment.getContent(),
                comment.isHasImage(),
                comment.getImage(),
                comment.isHasVideo(),
                comment.getVideo(),
                comment.isHasGif(),
                comment.getGif()
        );
    }


    private Map<UUID, UserResponse> fetchUserDetailsInBatches(List<String> userIds) {
        return userServiceClient.getUsersByIds(userIds).stream()
                .collect(Collectors.toMap(UserResponse::id, userResponse -> userResponse));
    }
}
