package com.nexos.api_user.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RoleRequestDto (
        @NotBlank(message = "{common.error.str.exact.size}")
        @Size(max = 25, message = "{error.role.code.size}")
        @Pattern(regexp = "^(?!.*--)[a-zA-Z]+(-[a-zA-Z]+)*$", message = "{error.role.code.invalid_pattern}")
        String code,
        @NotBlank(message = "{common.error.str.exact.size}")
        @Size(max = 25, message = "{error.role.name.size}")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "{error.role.name.invalid_pattern}")
        String name,
        @Size(max = 40, message = "{error.role.description.size}")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "{error.role.description.invalid_pattern}")
        String description
){
}
