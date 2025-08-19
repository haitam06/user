package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByUserId(Integer userId);
    List<Wishlist> findByProductId(Integer productId); 
}


