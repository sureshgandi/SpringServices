package com.spring.mysqldb.springdbservice.service;

import com.spring.mysqldb.springdbservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByRoleName(String roleName);

}
