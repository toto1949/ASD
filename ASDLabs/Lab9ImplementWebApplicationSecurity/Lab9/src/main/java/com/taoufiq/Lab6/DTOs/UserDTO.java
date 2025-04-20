package com.taoufiq.Lab6.DTOs;


import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private Set<RoleDTO> roles;
}
