package com.teamsparta8.productservice.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teamsparta8.productservice.domain.entity.Product;
import com.teamsparta8.productservice.domain.entity.SortBy;

public interface ProductQueryDslRepository {
	Page<Product> getProductSearch(String keyword, Pageable pageable, SortBy sortBy);
}
