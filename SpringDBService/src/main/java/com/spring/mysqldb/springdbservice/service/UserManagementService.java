package com.spring.mysqldb.springdbservice.service;

import com.spring.mysqldb.springdbservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserManagementService  extends JpaRepository<User,Long> {

    public boolean existsUserByUserName(String username);
    public User findUserByUserName(String username);
    //public Optional<List<User>> findUsersByOrganizationName(String orgName);

}
