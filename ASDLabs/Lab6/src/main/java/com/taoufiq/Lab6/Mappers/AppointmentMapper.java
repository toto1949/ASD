package com.taoufiq.Lab6.Mappers;

import com.taoufiq.Lab6.DTOs.AddressDTO;
import com.taoufiq.Lab6.DTOs.AppointmentDTO;
import com.taoufiq.Lab6.Models.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);
    Appointment appointmentDTOToAppointment(AppointmentDTO appointmentDTO);
}
