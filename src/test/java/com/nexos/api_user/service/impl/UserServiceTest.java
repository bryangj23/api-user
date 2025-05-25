package com.nexos.api_user.service.impl;

import com.nexos.api_user.dto.UserRequestDto;
import com.nexos.api_user.dto.UserResponseDto;
import com.nexos.api_user.dto.UserUpdateRequestDto;
import com.nexos.api_user.entity.Role;
import com.nexos.api_user.entity.User;
import com.nexos.api_user.repository.UserRepository;
import com.nexos.api_user.service.IRoleService;
import com.nexos.api_user.util.error.ErrorUtil;
import com.nexos.api_user.util.jpafilter.JpaFilterHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IRoleService roleService;

    @Mock
    private JpaFilterHelper jpaFilterHelper;

    @Mock
    private ErrorUtil errorUtil;

    private User user;
    private UserRequestDto userRequestDto;
    private UserUpdateRequestDto userUpdateRequestDto;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        role = Role.builder()
                .id(1L)
                .build();

        user = User.builder()
                .active(Boolean.TRUE)
                .build();

        userRequestDto = UserRequestDto.builder()
                .userNumber("233444555")
                .email("test@gmail.com")
                .name("Name Test")
                .secondName("Second Name Test")
                .lastNames("Last Name Test")
                .mothersSurname("Mother Surname Test")
                .roleId(1L)
                .build();

        userUpdateRequestDto = UserUpdateRequestDto.builder()
                .email("test@gmail.com")
                .name("Name Test")
                .secondName("Second Name Test")
                .lastNames("Last Name Test")
                .mothersSurname("Mother Surname Test")
                .roleId(1L)
                .build();
    }

    @Test
    @DisplayName("Test Get All Users")
    void testGetAllUsers() {
        Pageable pageable = mock(Pageable.class);
        String filters = "active==true";

        Page<User> page = new PageImpl<>(Collections.singletonList(user));
        when(userRepository.findAll(any(), eq(pageable))).thenReturn(page);

        Page<UserResponseDto> response = userService.getAll(pageable, filters);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        verify(userRepository).findAll(any(), eq(pageable));
    }

    @Test
    void testGetOne() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        UserResponseDto result = userService.getOne(1L);

        assertNotNull(result);
    }

    @Test
    void create() {
        when(userRepository.existsByUserNumber(anyString())).thenReturn(Boolean.FALSE);
        when(userRepository.existsByEmail(anyString())).thenReturn(Boolean.FALSE);
        when(roleService.getById(anyLong())).thenReturn(role);
        when(userRepository.save(any())).thenReturn(user);

        UserResponseDto result = userService.create(userRequestDto);

        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void update() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.existsByEmailAndIdNot(anyString(), anyLong())).thenReturn(Boolean.FALSE);
        when(roleService.getById(anyLong())).thenReturn(role);
        when(userRepository.save(any())).thenReturn(user);

        UserResponseDto result = userService.update(1L, userUpdateRequestDto);

        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void deactivate() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);

        userService.deactivate(1L);

        verify(userRepository).save(any(User.class));

    }
}