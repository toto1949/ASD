package com.taoufiq.Lab6.DTOs;

import lombok.*;

@Data
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO address;
}