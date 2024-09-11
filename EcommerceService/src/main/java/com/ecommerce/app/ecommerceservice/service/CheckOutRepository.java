package com.ecommerce.app.ecommerceservice.service;

import com.ecommerce.app.ecommerceservice.model.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut,Long> {

    Optional<CheckOut> findCheckOutByCreatedBy(String createdBy);
}
