package com.taoufiq.Lab6.Services;

import com.taoufiq.Lab6.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.taoufiq.Lab6.Repositories.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}