package com.taoufiq.Lab6.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taoufiq.Lab6.Models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

}

