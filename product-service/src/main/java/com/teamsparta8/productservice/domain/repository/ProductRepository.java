package com.teamsparta8.productservice.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teamsparta8.productservice.domain.entity.Product;
import com.teamsparta8.productservice.domain.entity.SortBy;

public interface ProductRepository {
	Product save(Product product);
	Product findById(UUID productId);

	Page<Product> getProductSearch(String keyword, Pageable pageable, SortBy sortBy);
}
