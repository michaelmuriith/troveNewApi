package com.devkenya.user.repository;

import com.devkenya.user.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, String> {
}
