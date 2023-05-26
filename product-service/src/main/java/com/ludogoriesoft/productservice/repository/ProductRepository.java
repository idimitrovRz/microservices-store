package com.ludogoriesoft.productservice.repository;

import com.ludogoriesoft.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
