package com.nexos.api_user.service.impl;

import com.nexos.api_user.dto.role.RoleRequestDto;
import com.nexos.api_user.dto.role.RoleResponseDto;
import com.nexos.api_user.entity.Role;
import com.nexos.api_user.mapper.RoleMapper;
import com.nexos.api_user.repository.RoleRepository;
import com.nexos.api_user.service.IRoleService;
import com.nexos.api_user.util.error.BaseError;
import com.nexos.api_user.util.error.ErrorUtil;
import com.nexos.api_user.util.jpafilter.JpaFilterHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private static final String ERROR_ROLE_NOT_FOUND = "error.role.not_found";
    private static final String ERROR_ROLE_EXIST_BY_CODE = "error.role.exist_by_code";
    private static final String ERROR_ROLE_EXIST_EXIST_USERS = "error.role.delete.exist_users";

    private final RoleRepository roleRepository;
    private final JpaFilterHelper jpaFilterHelper;
    private final ErrorUtil errorUtil;

    protected static final RoleMapper mapper = RoleMapper.INSTANCE;

    @Override
    public Role getById(Long id)  throws BaseError {
        return roleRepository.findById(id)
                .orElseThrow(() -> errorUtil.notFoundError(ERROR_ROLE_NOT_FOUND, Role.class));
    }

    @Override
    public Page<RoleResponseDto> getAll(Pageable pageable, String filters) throws BaseError {
        return getAllRolesByFilters(pageable, filters)
                .map(mapper::toDto);
    }

    private Page<Role> getAllRolesByFilters(Pageable pageable, String... filters) {
        return roleRepository.findAll(jpaFilterHelper.doOnEvery(Specification::and, filters),
                pageable
        );
    }

    @Override
    public RoleResponseDto create(RoleRequestDto request) throws BaseError {

        if(Boolean.TRUE.equals(roleRepository.existsByCode(request.code()))){
            throw errorUtil.businessError(ERROR_ROLE_EXIST_BY_CODE, Role.class);
        }

        Role role = mapper.toEntity(request);

        return mapper.toDto(roleRepository.save(role));
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDto request) throws BaseError {
        return roleRepository.findById(id)
                    .map(role -> {
                        if(Boolean.TRUE.equals(roleRepository.existsByCodeAndIdNot(request.code(), id))){
                            throw errorUtil.businessError(ERROR_ROLE_EXIST_BY_CODE, Role.class);
                        }
                        mapper.toUpdateEntity(request, role);
                        return role;
                    })
                    .map(roleRepository::save)
                    .map(mapper::toDto)
                    .orElseThrow(() -> errorUtil.notFoundError(ERROR_ROLE_NOT_FOUND, Role.class));
    }

    @Override
    public void delete(Long id) throws BaseError {

        Role role = getById(id);

        if(Boolean.TRUE.equals(roleRepository.existsUsersByRoleId(role.getId()))){
            throw errorUtil.businessError(ERROR_ROLE_EXIST_EXIST_USERS, Role.class);
        }

        roleRepository.delete(role);
    }

}
