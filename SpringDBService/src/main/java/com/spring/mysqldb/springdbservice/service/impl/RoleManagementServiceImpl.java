package com.spring.mysqldb.springdbservice.service.impl;

import com.spring.mysqldb.springdbservice.model.Role;
import com.spring.mysqldb.springdbservice.service.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleManagementServiceImpl {

    @Autowired
    RoleRepository roleRepository;

    public boolean isCheckRoleByName(String role) {
        return roleRepository.existsByRoleName(role);
    }

    public List<Role> createRoles(List<Role> roles){
        return roleRepository.saveAll(roles);
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

}
