package com.spring.mysqldb.springdbservice.service.impl;


import com.spring.mysqldb.springdbservice.model.User;
import com.spring.mysqldb.springdbservice.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementServiceImpl {
    UserManagementService userManagementService;

    @Autowired
    public void setUserManagementService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    public boolean isUserPresent(String name){
        return userManagementService.existsUserByUserName(name);
    }

    public User getUserByName(String name){
        return userManagementService.findUserByUserName(name);
    }

    public User createUser(User user) throws Exception {
        String userName=user.getUserName();
        user.setUid(user.getEmail());
        if(isUserPresent(userName)){
            throw new Exception("User already Existed please try different userName");
        }
        return userManagementService.save(user);
    }

    public User updateUser(User user) throws Exception {
        String userName=user.getUserName();
        if(!isUserPresent(userName)){
            throw new Exception("please create user first before creating userName");
        }
        return userManagementService.save(user);
    }

    public List<User> getAllUsers(){
        return userManagementService.findAll();
    }

}
