package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
