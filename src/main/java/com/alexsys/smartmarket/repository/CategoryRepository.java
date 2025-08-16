package com.alexsys.smartmarket.repository;

import com.alexsys.smartmarket.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
