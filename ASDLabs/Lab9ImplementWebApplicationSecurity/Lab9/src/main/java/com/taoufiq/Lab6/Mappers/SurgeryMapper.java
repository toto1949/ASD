package com.taoufiq.Lab6.Mappers;

import com.taoufiq.Lab6.DTOs.SurgeryDTO;
import com.taoufiq.Lab6.Models.Surgery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SurgeryMapper {
    SurgeryMapper INSTANCE = Mappers.getMapper(SurgeryMapper.class);

    SurgeryDTO surgeryToSurgeryDTO(Surgery surgery);
    Surgery surgeryDTOToSurgery(SurgeryDTO surgeryDTO);
}
