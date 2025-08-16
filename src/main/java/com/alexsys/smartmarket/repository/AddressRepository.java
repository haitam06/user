package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
