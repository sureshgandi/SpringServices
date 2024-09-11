package com.spring.mysqldb.springdbservice.service;

import com.spring.mysqldb.springdbservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {

    Optional<Organization> findOrganizationsByName(String name);

    boolean existsOrganizationByName(String name);


}
