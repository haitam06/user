package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}
