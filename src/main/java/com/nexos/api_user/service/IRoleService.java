package com.nexos.api_user.service;

import com.nexos.api_user.dto.role.RoleRequestDto;
import com.nexos.api_user.dto.role.RoleResponseDto;
import com.nexos.api_user.entity.Role;
import com.nexos.api_user.util.error.BaseError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoleService {

    Role getById(Long id)  throws BaseError;

    Page<RoleResponseDto> getAll(Pageable pageable, String filters) throws BaseError;

    RoleResponseDto create(RoleRequestDto request) throws BaseError;

    RoleResponseDto update(Long id, RoleRequestDto request) throws BaseError;

    void delete(Long id) throws BaseError;

}
