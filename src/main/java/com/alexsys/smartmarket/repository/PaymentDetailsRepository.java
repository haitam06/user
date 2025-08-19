package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer> {
    List<PaymentDetails> findByOrderDetailsId(Integer orderId);
}
