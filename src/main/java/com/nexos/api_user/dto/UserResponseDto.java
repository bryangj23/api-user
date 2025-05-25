package com.nexos.api_user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexos.api_user.dto.role.RoleResponseDto;

import java.time.LocalDateTime;

public record UserResponseDto(
       Long id,
       String userNumber,
       String email,
       String name,
       String secondName,
       String lastNames,
       String mothersSurname,
       Boolean active,
       RoleResponseDto role,
       @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
       LocalDateTime createdAt,
       @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
       LocalDateTime updatedAt
) {
}
