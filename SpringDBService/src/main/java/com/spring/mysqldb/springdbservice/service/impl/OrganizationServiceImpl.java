package com.spring.mysqldb.springdbservice.service.impl;

import com.spring.mysqldb.springdbservice.model.Organization;
import com.spring.mysqldb.springdbservice.service.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Optional<Organization> getOrganizationByName(String orgName){
        return  organizationRepository.findOrganizationsByName(orgName);
    }

    public boolean isOrganizationExists(String orgName){
       return organizationRepository.existsOrganizationByName(orgName);
    }
    public Organization saveOrganization(Organization org) throws Exception {
           String orgName=org.getName();

           if(isOrganizationExists(orgName)){
                throw new Exception("Organization name is exists with name= "+orgName);
            }
        return organizationRepository.save(org);
    }

    public Organization updateOrganization(Organization org) throws Exception {
        String orgName=org.getName();

        if(isOrganizationExists(orgName)){
            throw new Exception("Organization name is exists with name= "+orgName);
        }
        return organizationRepository.save(org);
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}
