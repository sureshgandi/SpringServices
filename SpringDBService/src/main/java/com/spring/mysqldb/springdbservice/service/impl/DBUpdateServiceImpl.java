package com.spring.mysqldb.springdbservice.service.impl;

import com.spring.mysqldb.springdbservice.dto.UserType;
import com.spring.mysqldb.springdbservice.model.Role;
import com.spring.mysqldb.springdbservice.model.User;
import org.springframework.boot.CommandLineRunner;

public class DBUpdateServiceImpl implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        User user=new User();
        user.setUid("admin");
        user.setEmail("admin");
        user.setUserName("admin");
        user.setPassword("admin");
        Role role=new Role();
        role.setRoleName(UserType.Administrator.name());
        //role.setRoleType(UserType.Administrator.name());
        user.getRole().add(role);
    }

    public User buildDefaultUser(){
        User user=new User();
        user.setUid("admin");
        user.setEmail("admin");
        user.setUserName("admin");
        user.setPassword("admin");
        Role role=new Role();
        role.setRoleName(UserType.Administrator.name());
        //role.setRoleType(UserType.Administrator.name());
        user.getRole().add(role);
        return  user;
    }
}
