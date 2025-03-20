package com.teamsparta8.productservice.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teamsparta8.productservice.application.dtos.ProductResponseInternalDto;
import com.teamsparta8.productservice.domain.entity.Product;
import com.teamsparta8.productservice.domain.entity.SortBy;

public interface ProductQueryDslRepository {
	//모든 상품 조회
	Page<Product> getProductSearch(String keyword, Pageable pageable, SortBy sortBy);
	//허브 담당자 상품 조회
	Page<Product> getProductSearchForHub(String keyword, Pageable pageable, SortBy sortBy, UUID hubId);
	//상품 상세 조회
	Optional<Product> findByProductId(UUID productId);
}
