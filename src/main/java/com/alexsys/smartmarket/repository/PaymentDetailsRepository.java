package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer> {
}
