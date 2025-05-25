package com.nexos.api_user.controller;

import com.nexos.api_user.dto.UserPathParamDto;
import com.nexos.api_user.dto.UserRequestDto;
import com.nexos.api_user.dto.UserResponseDto;
import com.nexos.api_user.dto.UserUpdateRequestDto;
import com.nexos.api_user.service.IUserService;
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
public class UserController extends BaseController {

    private final IUserService userService;
    private static final String COUNT_HEADER_NAME = "count";

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAll(Pageable pageable,
                                                        @RequestParam(required = false, defaultValue = "") String filters)
            throws BaseError {

        Page<UserResponseDto> user = userService.getAll(pageable, filters);
        return ResponseEntity.ok()
                .header(COUNT_HEADER_NAME, String.valueOf(user.getTotalElements()))
                .body(user.stream().toList());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> getOne(@Valid UserPathParamDto userPathParamDto) throws BaseError {

        return ResponseEntity.ok()
                .body(userService.getOne(userPathParamDto.getUserId()));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto userRequestDto) throws BaseError {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(userRequestDto));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> update(
            @Valid UserPathParamDto userPathParamDto,
            @RequestBody @Valid UserUpdateRequestDto userRequestDto) throws BaseError {
        return ResponseEntity
                .ok(userService.update(userPathParamDto.getUserId(), userRequestDto));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deactivate(@Valid UserPathParamDto userPathParamDto) throws BaseError {
        userService.deactivate(userPathParamDto.getUserId());
        return ResponseEntity.noContent().build();
    }

}
