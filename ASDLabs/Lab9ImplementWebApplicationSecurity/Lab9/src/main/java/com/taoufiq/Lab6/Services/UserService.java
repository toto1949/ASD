package com.taoufiq.Lab6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taoufiq.Lab6.Models.Role;
import com.taoufiq.Lab6.Models.User;
import com.taoufiq.Lab6.Repositories.RoleRepository;
import com.taoufiq.Lab6.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use.");
        }
    
        if (user.getPassword() == null) {
            throw new RuntimeException("Password cannot be null");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
    
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName("ROLE_USER");
                        return roleRepository.save(newRole);
                    });
            user.setRoles(List.of(defaultRole)); 
        }
    
        return userRepository.save(user);
    }
    

}
