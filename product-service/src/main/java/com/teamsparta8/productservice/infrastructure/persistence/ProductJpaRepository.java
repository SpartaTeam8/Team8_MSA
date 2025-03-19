package com.teamsparta8.productservice.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsparta8.productservice.domain.entity.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, UUID> {
}
