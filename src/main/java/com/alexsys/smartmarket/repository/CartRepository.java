package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
