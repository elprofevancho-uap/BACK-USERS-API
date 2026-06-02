package com.uap.user.dto.mapper;

import com.uap.user.dto.entity.User;
import com.uap.user.dto.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Si los nombres de los atributos fueran idénticos, no necesitarías los @Mapping
    @Mapping(source = "externalId", target = "id")
    @Mapping(source = "createdDate", target = "created")
    @Mapping(source = "updateDate", target = "lastUpdated")
    UserResponse toDTO(User usuario);

}