package com.taoufiq.Lab6.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taoufiq.Lab6.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
