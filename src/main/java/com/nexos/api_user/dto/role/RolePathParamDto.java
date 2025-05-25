package com.nexos.api_user.dto.role;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePathParamDto {
    @NotBlank(message = "{common.error.required.field}")
    @Size(min = 1, message = "{common.error.str.exact.size}")
    @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "{common.error.integer}")
    @Positive(message = "{common.error.positive}")
    private String roleId;

    public Long getRoleId() {
        return Long.parseLong(this.roleId);
    }
}
