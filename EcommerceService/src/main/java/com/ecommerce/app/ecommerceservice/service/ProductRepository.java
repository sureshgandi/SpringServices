package com.ecommerce.app.ecommerceservice.service;

import com.ecommerce.app.ecommerceservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByName(String name);
    Optional<List<Product>> findProductsByName(String name);
}
