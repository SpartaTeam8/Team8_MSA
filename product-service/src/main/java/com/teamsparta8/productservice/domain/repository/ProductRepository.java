package com.teamsparta8.productservice.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsparta8.productservice.domain.entity.Product;
import com.teamsparta8.productservice.infrastructure.persistence.CustomProductRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, CustomProductRepository {
}
