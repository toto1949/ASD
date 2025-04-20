package com.taoufiq.Lab6.Mappers;

import com.taoufiq.Lab6.DTOs.RoleDTO;
import com.taoufiq.Lab6.Models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);
}
