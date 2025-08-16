package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.ProductsSku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsSkuRepository extends JpaRepository<ProductsSku, Integer> {
}
