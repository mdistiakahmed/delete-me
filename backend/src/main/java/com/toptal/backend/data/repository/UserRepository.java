package com.toptal.backend.data.repository;

import com.toptal.backend.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    long deleteByEmail(String email);
}
