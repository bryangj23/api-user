package com.nexos.api_user.mapper;

import com.nexos.api_user.dto.role.RoleRequestDto;
import com.nexos.api_user.dto.role.RoleResponseDto;
import com.nexos.api_user.entity.Role;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toEntity(RoleRequestDto request);

    void toUpdateEntity(RoleRequestDto request, @MappingTarget Role entity);

    RoleResponseDto toDto(Role entity);

}
