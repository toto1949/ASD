package com.taoufiq.Lab6.DTOs;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Set<String> roleNames;

    // Getter for roleNames to avoid null
    public Set<String> getRoleNames() {
        if (roleNames == null) {
            roleNames = new HashSet<>(); // Initialize as empty set if null
        }
        return roleNames;
    }

    // Setter for roleNames (no changes needed, unless you want additional logic)
    public void setRoleNames(Set<String> roleNames) {
        this.roleNames = roleNames;
    }
}
