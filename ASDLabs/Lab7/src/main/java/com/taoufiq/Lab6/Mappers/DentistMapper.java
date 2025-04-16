package com.taoufiq.Lab6.Mappers;

import com.taoufiq.Lab6.DTOs.DentistDTO;
import com.taoufiq.Lab6.Models.Dentist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DentistMapper {
    DentistMapper INSTANCE = Mappers.getMapper(DentistMapper.class);

    DentistDTO dentistToDentistDTO(Dentist dentist);
    Dentist dentistDTOToDentist(DentistDTO dentistDTO);
}
