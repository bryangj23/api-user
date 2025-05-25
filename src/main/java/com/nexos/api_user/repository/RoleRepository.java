package com.nexos.api_user.repository;

import com.nexos.api_user.entity.Role;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    Boolean existsByCode(String code);
    Boolean existsByCodeAndIdNot(String code, Long id);

    @Query("SELECT COUNT(*) > 0 FROM User WHERE role.id = :id")
    Boolean existsUsersByRoleId(Long id);
}
