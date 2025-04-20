package com.taoufiq.Lab6.DTOs;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentDTO {
    private Long id;
    private LocalDateTime appointmentDateTime;
    private PatientDTO patient;
    private DentistDTO dentist;
    private SurgeryDTO surgery;
}

