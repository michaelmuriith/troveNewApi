package com.devkenya.user.repository;

import com.devkenya.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    List<User> findAllByIdNot(String id);
}
