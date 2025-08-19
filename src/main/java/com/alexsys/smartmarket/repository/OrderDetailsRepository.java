package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.OrderDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByUserId(Integer userId);
}