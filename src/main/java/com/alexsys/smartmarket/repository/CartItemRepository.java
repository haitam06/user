package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
