package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
