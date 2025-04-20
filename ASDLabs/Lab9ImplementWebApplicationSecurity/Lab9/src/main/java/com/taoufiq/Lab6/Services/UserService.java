package com.taoufiq.Lab6.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    // Add this new method to your existing UserService
    public void addRolesToUser(String username, List<String> roleNames) {
        User user = userRepository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Set<Role> roles = user.getRoles();
        if (roles == null) {
            roles = new HashSet<>();
        }
        
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(roleName);
                        return roleRepository.save(newRole);
                    });
            roles.add(role);
        }
        
        user.setRoles(roles);
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsernameWithRoles(username).isPresent();
    }
    
    public User registerUser(String username, String password, Set<String> roleNames) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
    
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
    
        user.setRoles(roles);
        return userRepository.save(user);
    }
    
}
