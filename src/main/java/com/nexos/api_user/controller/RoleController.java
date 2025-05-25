package com.nexos.api_user.controller;

import com.nexos.api_user.dto.role.RolePathParamDto;
import com.nexos.api_user.dto.role.RoleRequestDto;
import com.nexos.api_user.dto.role.RoleResponseDto;
import com.nexos.api_user.service.IRoleService;
import com.nexos.api_user.util.error.BaseError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoleController extends BaseController {

    private final IRoleService roleService;
    private static final String COUNT_HEADER_NAME = "count";

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponseDto>> getAll(
            Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String filters) throws BaseError {

        Page<RoleResponseDto> user = roleService.getAll(pageable, filters);
        return ResponseEntity.ok()
                .header(COUNT_HEADER_NAME, String.valueOf(user.getTotalElements()))
                .body(user.stream().toList());
    }

    @PostMapping("/roles")
    public ResponseEntity<RoleResponseDto> create(@RequestBody @Valid RoleRequestDto roleRequestDto) throws BaseError {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleService.create(roleRequestDto));
    }

    @PutMapping("/roles/{roleId}")
    public ResponseEntity<RoleResponseDto> update(
            @Valid RolePathParamDto rolePathParamDto,
            @RequestBody @Valid RoleRequestDto roleRequestDto) throws BaseError {
        return ResponseEntity
                .ok(roleService.update(rolePathParamDto.getRoleId(), roleRequestDto));
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<Void> delete(@Valid RolePathParamDto rolePathParamDto) throws BaseError {
        roleService.delete(rolePathParamDto.getRoleId());
        return ResponseEntity.noContent().build();
    }
}
