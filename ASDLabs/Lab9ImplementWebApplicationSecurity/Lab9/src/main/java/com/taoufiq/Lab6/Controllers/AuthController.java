package com.taoufiq.Lab6.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taoufiq.Lab6.DTOs.AuthRequest;
import com.taoufiq.Lab6.DTOs.AuthResponse;
import com.taoufiq.Lab6.DTOs.UserDTO;
import com.taoufiq.Lab6.Mappers.UserMapper;
import com.taoufiq.Lab6.Models.User;
import com.taoufiq.Lab6.Security.JWTManagementUtilityService;
import com.taoufiq.Lab6.Services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTManagementUtilityService jwtManagementUtilityService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JWTManagementUtilityService jwtManagementUtilityService,
                          UserService userService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtManagementUtilityService = jwtManagementUtilityService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new AuthResponse("Invalid username or password"));
        }

        String token = jwtManagementUtilityService.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        try {
            User user = userMapper.userDTOToUser(userDTO);
            
            userService.registerUser(user); 
            return ResponseEntity.ok("Registration successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }
    
}
