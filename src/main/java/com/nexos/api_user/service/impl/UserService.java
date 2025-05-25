package com.nexos.api_user.service.impl;

import com.nexos.api_user.dto.UserRequestDto;
import com.nexos.api_user.dto.UserResponseDto;
import com.nexos.api_user.dto.UserUpdateRequestDto;
import com.nexos.api_user.entity.User;
import com.nexos.api_user.mapper.UserMapper;
import com.nexos.api_user.repository.UserRepository;
import com.nexos.api_user.service.IRoleService;
import com.nexos.api_user.service.IUserService;
import com.nexos.api_user.util.error.*;
import com.nexos.api_user.util.jpafilter.JpaFilterHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private static final String ERROR_USER_NOT_FOUND = "error.user.not_found";
    private static final String ERROR_USER_CONFLICT_USER_NUMBER = "error.user.conflict.user_number";
    private static final String ERROR_USER_CONFLICT_EMAIL = "error.user.conflict.email";

    private final UserRepository userRepository;
    private final IRoleService roleService;
    private final JpaFilterHelper jpaFilterHelper;
    private final ErrorUtil errorUtil;

    protected static final UserMapper mapper = UserMapper.INSTANCE;

    @Override
    public Page<UserResponseDto> getAll(Pageable pageable, String filters) throws BaseError {
        return getAllUsersByFilters(pageable, filters)
                .map(mapper::toDto);
    }

    private Page<User> getAllUsersByFilters(Pageable pageable, String... filters) {
        return userRepository.findAll(jpaFilterHelper.doOnEvery(Specification::and, filters),
                pageable
        );
    }

    @Override
    public UserResponseDto getOne(Long id)  throws BaseError {
        return userRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> errorUtil.notFoundError(ERROR_USER_NOT_FOUND, User.class));
    }

    @Override
    public UserResponseDto create(UserRequestDto request)  throws BaseError {

        if(Boolean.TRUE.equals(userRepository.existsByUserNumber(request.userNumber()))){
            throw errorUtil.businessError(ERROR_USER_CONFLICT_USER_NUMBER, User.class);
        }

        if(Boolean.TRUE.equals(userRepository.existsByEmail(request.email()))){
            throw errorUtil.businessError(ERROR_USER_CONFLICT_EMAIL, User.class);
        }

        User user = mapper.toEntity(request).toBuilder()
                .role(roleService.getById(request.roleId()))
                .build();

        return mapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserUpdateRequestDto request) throws BaseError {

        return userRepository.findById(id)
                .map(user -> {
                    if(Boolean.TRUE.equals(userRepository.existsByEmailAndIdNot(request.email(), user.getId()))){
                        throw errorUtil.businessError(ERROR_USER_CONFLICT_EMAIL, User.class);
                    }
                    mapper.toUpdateEntity(request, user);
                    user.setRole(roleService.getById(request.roleId()));
                    return user;
                })
                .map(userRepository::save)
                .map(mapper::toDto)
                .orElseThrow(() -> errorUtil.notFoundError(ERROR_USER_NOT_FOUND, User.class));
    }

    @Override
    public void deactivate(Long id) throws BaseError {
        userRepository.findById(id)
                .map(user -> {
                    user.setActive(Boolean.FALSE);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> errorUtil.notFoundError(ERROR_USER_NOT_FOUND, User.class));
        log.info("Soft delete applied to user with id {}", id);
    }

}
