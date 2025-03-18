package com.teamsparta8.productservice.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.teamsparta8.productservice.domain.entity.Product;
import com.teamsparta8.productservice.domain.entity.SortBy;
import com.teamsparta8.productservice.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
	private final ProductJpaRepository productJpaRepository;
	private final ProductQueryDslRepository productQueryDslRepository;


	@Override
	public Product save(Product product) {
		return productJpaRepository.save(product);
	}

	@Override
	public Product findById(UUID productId) {
		return productJpaRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
	}

	@Override
	public Page<Product> getProductSearch(String keyword, Pageable pageable, SortBy sortBy) {
		return productQueryDslRepository.getProductSearch(keyword, pageable, sortBy);
	}
}
