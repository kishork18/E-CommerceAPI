package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  public Optional<Product> findByTitle(String title);
}
