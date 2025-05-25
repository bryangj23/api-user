package com.nexos.api_user.service.impl;

import com.nexos.api_user.dto.role.RoleRequestDto;
import com.nexos.api_user.dto.role.RoleResponseDto;
import com.nexos.api_user.entity.Role;
import com.nexos.api_user.repository.RoleRepository;
import com.nexos.api_user.util.error.ErrorUtil;
import com.nexos.api_user.util.jpafilter.JpaFilterHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JpaFilterHelper jpaFilterHelper;

    @Mock
    private ErrorUtil errorUtil;

    private Role role;
    private RoleRequestDto roleRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        role = Role.builder()
                .id(1L)
                .code("administrator")
                .build();

        roleRequestDto = RoleRequestDto.builder()
                .code("administrator")
                .name("Name Role Test")
                .description("Description Role Test")
                .build();
    }

    @Test
    void getById() {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));

        Role result = roleService.getById(1L);

        assertNotNull(result);
    }

    @Test
    void getAll() {
        Pageable pageable = mock(Pageable.class);
        String filters = "code=='administrator'";

        Page<Role> page = new PageImpl<>(Collections.singletonList(role));
        when(roleRepository.findAll(any(), eq(pageable))).thenReturn(page);

        Page<RoleResponseDto> response = roleService.getAll(pageable, filters);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        verify(roleRepository).findAll(any(), eq(pageable));
    }

    @Test
    void create() {
        when(roleRepository.existsByCode(anyString())).thenReturn(Boolean.FALSE);
        when(roleRepository.save(any())).thenReturn(role);

        RoleResponseDto result = roleService.create(roleRequestDto);

        assertNotNull(result);
        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void update() {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));
        when(roleRepository.existsByCodeAndIdNot(anyString(), anyLong())).thenReturn(Boolean.FALSE);
        when(roleRepository.save(any())).thenReturn(role);

        RoleResponseDto result = roleService.update(1L, roleRequestDto);

        assertNotNull(result);
        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void delete() {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));
        when(roleRepository.existsUsersByRoleId(anyLong())).thenReturn(Boolean.FALSE);
        doNothing().when(roleRepository).delete(any(Role.class));

        roleService.delete(1L);

        verify(roleRepository).delete(any(Role.class));
    }
}