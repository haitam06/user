package com.alexsys.smartmarket.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexsys.smartmarket.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
