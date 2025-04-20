package com.taoufiq.Lab6.DTOs;


import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
