package com.teamsparta8.productservice.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teamsparta8.productservice.domain.entity.Product;

@Service
public class ProductService {
	public Page<Product> findAllProducts(Pageable pageable) {
		return null;
	}
}
