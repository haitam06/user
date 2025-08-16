package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
