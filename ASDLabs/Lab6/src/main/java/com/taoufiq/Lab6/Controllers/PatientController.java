package com.taoufiq.Lab6.Controllers;

import com.taoufiq.Lab6.Models.Patient;
import com.taoufiq.Lab6.Services.PatientService;
import com.taoufiq.Lab6.DTOs.PatientDTO;
import com.taoufiq.Lab6.Mappers.PatientMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final PatientMapper patientMapper = PatientMapper.INSTANCE;

    @GetMapping
    public List<PatientDTO> getAll() {
        return patientService.findAll().stream()
                             .map(patientMapper::patientToPatientDTO)
                             .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<PatientDTO> create(@RequestBody @Valid Patient patient) {
        Patient savedPatient = patientService.save(patient);
        return ResponseEntity.ok(patientMapper.patientToPatientDTO(savedPatient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable Long id) {
        Patient found = patientService.findById(id);
        return found != null ? ResponseEntity.ok(patientMapper.patientToPatientDTO(found)) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable Long id, @RequestBody @Valid Patient patient) {
        patient.setId(id);
        Patient updatedPatient = patientService.save(patient);
        return ResponseEntity.ok(patientMapper.patientToPatientDTO(updatedPatient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
