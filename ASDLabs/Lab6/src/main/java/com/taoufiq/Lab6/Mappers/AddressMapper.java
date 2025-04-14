package com.taoufiq.Lab6.Mappers;

import com.taoufiq.Lab6.DTOs.AddressDTO;
import com.taoufiq.Lab6.Models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO addressToAddressDTO(Address address);

   Address addressDTOToAddress(AddressDTO dto);
}
