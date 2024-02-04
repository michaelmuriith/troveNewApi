package com.devkenya.post.service;

import com.devkenya.post.dto.req.CreatePostReq;
import com.devkenya.post.model.Post;
import com.devkenya.post.repository.PostRepository;
import com.devkenya.user.model.User;
import com.devkenya.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //create a post
    public boolean createPost(CreatePostReq createPostReq) {
        log.info("Creating post for user with id: {}", createPostReq.userId());
        var userId = createPostReq.userId();

        User user = userRepository.findById(userId).orElse(null); // Assuming you have a UserRepository

        if (user == null) {
            log.error("User not found with id: {}", userId);
            return false;
        }

        Post post = Post.builder()
                .user(user)
                .hasImage(createPostReq.image() != null)
                .image(createPostReq.image())
                .content(createPostReq.content())
                .build();

        // Save the post to the database
        postRepository.save(post);

        return true;
    }

}
