package com.nexos.api_user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record UserUpdateRequestDto (
        @NotBlank(message = "{common.error.required.field}")
        @Pattern(regexp = "^(?=.{1,320}$)(?=.{1,64}@)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "{error.user.email.invalid}")
        @Size(max = 255, message = "{error.user.email.invalid_max_length}")
        String email,
        @NotBlank(message = "{common.error.required.field}")
        @Size(min = 1, max = 50, message = "{error.user.name.invalid_max_length}")
        @Pattern(regexp = "^[a-zA-ZñÑ]+( [a-zA-ZñÑ]+)*$",message = "{error.user.name.invalid}")
        String name,
        @Size(max = 50,message = "{error.user.second_name.invalid_max_length}")
        @Pattern(regexp = "^[a-zA-ZñÑ]+( [a-zA-ZñÑ]+)*$", message = "{error.user.second_name.invalid_pattern}")
        String secondName,
        @NotBlank(message = "{common.error.required.field}")
        @Size(min = 1, max = 100,message = "{error.user.last.name.max.long.invalid}")
        @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "{error.user.last_name.invalid}")
        String lastNames,
        @Size(max = 100, message = "{error.user.mothers.surname.max.long.pattern}")
        @Pattern(regexp = "^[a-zA-ZñÑ ]+$" ,message = "{error.user.mothers_surname.invalid}")
        String mothersSurname,
        @NotNull(message = "{common.error.required.field}")
        @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "{common.error.integer}")
        @Positive(message = "{common.error.positive}")
        Long roleId
) {
}
