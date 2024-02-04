package com.devkenya.user.service;

import com.devkenya.user.model.Follow;
import com.devkenya.user.repository.FollowRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    public void followUser(String userId, String followId) {
        log.info("Following user with id: {}", userId);
        Follow follow = Follow.builder()
                .followerId(userId)
                .followingId(followId)
                .build();
        followRepository.saveAndFlush(follow);
    }

    public void unfollowUser(String userId, String followId) {
        log.info("Unfollowing user with id: {}", userId);
        Follow follow = Follow.builder()
                .followerId(userId)
                .followingId(followId)
                .build();
        followRepository.delete(follow);
    }
}
