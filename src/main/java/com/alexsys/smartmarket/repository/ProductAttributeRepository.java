package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
}
