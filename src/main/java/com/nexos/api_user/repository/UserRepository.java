package com.nexos.api_user.repository;

import com.nexos.api_user.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    Boolean existsByUserNumber(String userNumber);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndIdNot(String email, Long id);

}
