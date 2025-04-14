package com.taoufiq.Lab6.Mappers;

import com.taoufiq.Lab6.DTOs.UserDTO;
import com.taoufiq.Lab6.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    @Mapping(target = "password", ignore = true)
    User userDTOToUser(UserDTO userDTO); 
}
