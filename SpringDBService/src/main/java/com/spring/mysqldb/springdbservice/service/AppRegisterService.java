package com.spring.mysqldb.springdbservice.service;

import com.spring.mysqldb.springdbservice.model.AppRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRegisterService extends JpaRepository<AppRegister,Long> {

    boolean existsAppRegisterByAppName(String appName);
    Optional<AppRegister> findAppRegisterByAppName(String appName);
}
