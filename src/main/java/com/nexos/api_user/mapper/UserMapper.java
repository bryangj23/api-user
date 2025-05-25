package com.nexos.api_user.mapper;

import com.nexos.api_user.dto.UserRequestDto;
import com.nexos.api_user.dto.UserResponseDto;
import com.nexos.api_user.dto.UserUpdateRequestDto;
import com.nexos.api_user.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "active", expression = "java(Boolean.TRUE)")
    User toEntity(UserRequestDto request);

    void toUpdateEntity(UserUpdateRequestDto request, @MappingTarget User user);

    UserResponseDto toDto(User entity);

}
