package com.spring.mysqldb.springdbservice.web;

import com.spring.mysqldb.springdbservice.model.Organization;
import com.spring.mysqldb.springdbservice.model.User;
import com.spring.mysqldb.springdbservice.service.impl.OrganizationServiceImpl;
import com.spring.mysqldb.springdbservice.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebController {


    @Autowired
    OrganizationServiceImpl organizationServiceImpl;

    @Autowired
    UserManagementServiceImpl userManagementService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public User createUser(@RequestBody User user) throws Exception {
        return userManagementService.createUser(user);
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) throws Exception {
        return userManagementService.updateUser(user);
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> findAllUsers(){
        return userManagementService.getAllUsers();
    }

    @RequestMapping(value = "/organization",method = RequestMethod.POST)
    public Organization createOrganization(@RequestBody Organization org) throws Exception {
        return organizationServiceImpl.saveOrganization(org);
    }

    @RequestMapping(value = "/organization",method = RequestMethod.PUT)
    public Organization updateOrganization(@RequestBody Organization org) throws Exception {
        return organizationServiceImpl.updateOrganization(org);
    }

    @RequestMapping(value = "/organizations",method = RequestMethod.GET)
    public List<Organization> findAllOrganizations(){
        return organizationServiceImpl.getAllOrganizations();
    }
}
