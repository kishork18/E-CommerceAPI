package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
   public Optional<Category> findByName(String name);
}
