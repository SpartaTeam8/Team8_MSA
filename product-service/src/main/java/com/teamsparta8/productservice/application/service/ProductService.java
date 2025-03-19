package com.teamsparta8.productservice.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsparta8.productservice.application.dtos.ProductCreateInternalDto;
import com.teamsparta8.productservice.application.dtos.ProductResponseInternalDto;
import com.teamsparta8.productservice.application.dtos.ProductUpdateInternalDto;
import com.teamsparta8.productservice.domain.entity.Product;
import com.teamsparta8.productservice.domain.entity.SortBy;
import com.teamsparta8.productservice.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	//상품 등록
	@Transactional
	public ProductResponseInternalDto createProduct(UUID userId, ProductCreateInternalDto requestDto) {
		log.info("request DTO hubId {}  : ", requestDto.getHubId());
		//TODO 생성 권한이 있는 사람인 지 체크(본인 업체, 담당 허브)
		//TODO 업체, 허브가 존재하는 지 체크

		//상품 정보 등록
		//ProductCreateInternalDto -> Product Entity
		Product product = productMapper.createToProduct(requestDto);
		//Product DB 저장
		Product savedProduct = productRepository.save(product);

		return productMapper.productToResponse(savedProduct);
	}
	
	//상품 수정
	@Transactional
	public ProductResponseInternalDto updateProduct(UUID productId, ProductUpdateInternalDto requestDto) {

		//TODO 수정 권한이 있는 사람인 지 체크(본인 업체, 담당 허브)

		//상품 정보 수정
		Product product = productRepository.findById(productId);

		productMapper.updateToProduct(requestDto, product);

		/*log.info("product mapper : {} ", requestDto.getProductName());
		log.info("product mapper : {} ", product.getProductName());*/

		return productMapper.productToResponse(product);
	}
	
	//상품 삭제
	@Transactional
	public void deleteProduct(UUID productId) {

		//TODO 삭제 권한이 있는 사람인 지 체크(본인 업체, 담당 허브)


		//상품 삭제 (soft delete)
		Product product = productRepository.findById(productId);

		product.setDeleted(true);
	}

	//상품 목록 조회
	public Page<ProductResponseInternalDto> getProductSearch(String keyword, Pageable pageable, SortBy sortBy) {

		Page<Product> productList = productRepository.getProductSearch(keyword, pageable, sortBy);

		return productMapper.productListToResponse(productList);
	}

	//특정 상품 조회
	public ProductResponseInternalDto getProductDetails(UUID productId) {

		Product product = productRepository.findById(productId);

		return productMapper.productToResponse(product);
	}

}
