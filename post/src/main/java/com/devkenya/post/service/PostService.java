package com.devkenya.post.service;

import com.devkenya.clients.user.UserResponse;
import com.devkenya.clients.user.UserServiceClient;
import com.devkenya.post.dto.PostsResItem;
import com.devkenya.post.dto.req.CreatePostReq;
import com.devkenya.post.model.Post;
import com.devkenya.post.repository.CommentRepository;
import com.devkenya.post.repository.LikePostRepository;
import com.devkenya.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserServiceClient userServiceClient;
    private final LikePostRepository likePostRepository;
    private final CommentRepository commentRepository;

    public Post createPost(CreatePostReq createPostReq) {
        log.info("Creating post for user with id: {}", createPostReq.userId());
        var userId = createPostReq.userId();
        UserResponse user = userServiceClient.getUserById(userId);
        if (user == null || user.id() == null) {
            log.error("User with id {} not found. Unable to create post.", userId);
            return null;
        }

        Post post = Post.builder().userId(user.id()).content(createPostReq.content()).hasImage(createPostReq.image() != null).image(createPostReq.image()).hasVideo(createPostReq.video() != null).video(createPostReq.video()).hasGif(createPostReq.gif() != null).gif(createPostReq.gif()).build();

        postRepository.saveAndFlush(post);
        return post;
    }

    public Page<PostsResItem> getPosts(int page, int size, String currentUserId) {
        try {
            log.info("Fetching posts for user with id: {} page: {} size: {}", currentUserId, page, size);
            Page<Post> postPage = postRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));
            log.info("Fetched posts: {}", postPage.getContent().size());

            List<String> userIds = postPage.getContent().stream().map(post -> post.getUserId().toString()).collect(Collectors.toList());

            log.info("User ids: {}", userIds);

            Map<UUID, UserResponse> userDetailsMap = fetchUserDetailsInBatches(userIds);
            log.info("User details map: {}", userDetailsMap);

            Set<String> postIds = postPage.getContent().stream().map(post -> post.getId().toString()).collect(Collectors.toSet());

            log.info("Post ids: {}", postIds);

            Map<String, Boolean> hasLikedMap = getHasLikedMap(postIds, currentUserId);

            List<PostsResItem> postsResItems = postPage.getContent().stream().map(post -> mapToPostsResItem(post, userDetailsMap, hasLikedMap)).collect(Collectors.toList());

            return new PageImpl<>(postsResItems, postPage.getPageable(), postPage.getTotalElements());
        } catch (Exception e) {
            log.error("Error fetching posts", e);
            return new PageImpl<>(List.of());
        }
    }

    private Map<String, Boolean> getHasLikedMap(Set<String> postIds, String currentUserId) {
        return postIds.stream().collect(Collectors.toMap(postId -> postId, postId -> likePostRepository.existsByPostIdAndUserId(UUID.fromString(postId), UUID.fromString(currentUserId))));
    }

    private PostsResItem mapToPostsResItem(Post post, Map<UUID, UserResponse> userDetailsMap, Map<String, Boolean> hasLikedMap) {
        UserResponse userResponse = userDetailsMap.get(post.getUserId());
        return new PostsResItem(post.getId(), userResponse, post.getContent(), post.isHasImage(), post.getImage(), post.isHasVideo(), post.getVideo(), post.isHasGif(), post.getGif(), hasLikedMap.getOrDefault(post.getId().toString(), false), post.getCreatedAt().toString());
    }

    private Map<UUID, UserResponse> fetchUserDetailsInBatches(List<String> userIds) {
        Map<UUID, UserResponse> userDetailsMap = new HashMap<>();
        List<UserResponse> userResponses = userServiceClient.getUsersByIds(userIds);

        // Convert List to Map
        for (UserResponse userResponse : userResponses) {
            userDetailsMap.put(userResponse.id(), userResponse);
        }

        return userDetailsMap;
    }

}
