package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.RolesAndAuthority;

public interface RolesAndAuthorityRepository extends JpaRepository<RolesAndAuthority, Long> {
	Optional<RolesAndAuthority> findByName(String name);
}
