package com.taoufiq.Lab6.Controllers;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taoufiq.Lab6.DTOs.AuthRequest;
import com.taoufiq.Lab6.DTOs.AuthResponse;
import com.taoufiq.Lab6.DTOs.RegisterRequest;
import com.taoufiq.Lab6.Models.User;
import com.taoufiq.Lab6.Repositories.RoleRepository;
import com.taoufiq.Lab6.Repositories.UserRepository;
import com.taoufiq.Lab6.Security.JwtUtil;
import com.taoufiq.Lab6.Services.UserService;

import jakarta.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

@PostMapping("/login")
public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
    } catch (BadCredentialsException e) {
        return ResponseEntity.status(401).build();
    }

    // Retrieve the user from the repository to get their roles
    User user = userRepository.findByUsernameWithRoles(authRequest.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    // Get the user's roles and map them to a Set of strings
    Set<String> roles = user.getRoles().stream()
            .map(role -> role.getName()) // Map to role names (strings)
            .collect(Collectors.toSet()); // Collect into a Set

    // Generate the token with roles included
    String token = jwtUtil.generateToken(authRequest.getUsername(), roles);

    return ResponseEntity.ok(new AuthResponse(token));
}

    
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        try {
            userService.registerUser(request.getUsername(), request.getPassword(), request.getRoleNames());
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
    
}
