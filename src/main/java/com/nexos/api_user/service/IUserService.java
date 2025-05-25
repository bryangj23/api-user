package com.nexos.api_user.service;

import com.nexos.api_user.dto.UserRequestDto;
import com.nexos.api_user.dto.UserResponseDto;
import com.nexos.api_user.dto.UserUpdateRequestDto;
import com.nexos.api_user.util.error.BaseError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    Page<UserResponseDto> getAll(Pageable pageable, String filters)  throws BaseError;

    UserResponseDto getOne(Long id)  throws BaseError;

    UserResponseDto create(UserRequestDto request)  throws BaseError;

    UserResponseDto update(Long id, UserUpdateRequestDto request) throws BaseError;

    void deactivate(Long id) throws BaseError;

}
