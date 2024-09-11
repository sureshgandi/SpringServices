package com.spring.mysqldb.springdbservice.model;

import jakarta.persistence.*;
@Entity
@Table(name = "role_details")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_Id;

    private String roleName;

    private String createdOn;

    public Long getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(Long role_Id) {
        this.role_Id = role_Id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
