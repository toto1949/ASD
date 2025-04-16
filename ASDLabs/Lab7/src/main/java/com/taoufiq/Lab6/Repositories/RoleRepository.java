package com.taoufiq.Lab6.Repositories;

import com.taoufiq.Lab6.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Patient, Long> {}

