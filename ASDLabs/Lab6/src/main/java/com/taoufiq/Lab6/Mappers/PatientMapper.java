package com.taoufiq.Lab6.Mappers;


import com.taoufiq.Lab6.Models.Patient;
import com.taoufiq.Lab6.DTOs.PatientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDTO patientToPatientDTO(Patient patient);
    Patient patientDTOToPatient(PatientDTO patientDTO);
}
