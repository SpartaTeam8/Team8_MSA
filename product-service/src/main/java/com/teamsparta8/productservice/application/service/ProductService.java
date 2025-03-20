package com.teamsparta8.productservice.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsparta8.productservice.application.dtos.CompanyResponseDto;
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

	//Feign Client
	// private final HubFeignClient hubFeignClient;
	// private final CompanyFeignClient companyFeignClient;

	//상품 등록
	@Transactional
	public ProductResponseInternalDto createProduct(
		// Long userId,
		// String userRole,
		ProductCreateInternalDto requestDto
	) {
		//TODO 상품 등록 권한 처리 (본인 업체, 담당 허브)
		/*if (userRole.equals("DELIVERY")) {
			throw new IllegalArgumentException("배송자는 상품 등록 권한이 없습니다.");
		}

		CompanyResponseDto companyInfo = companyServiceClient.getCompanyById(userId);
		if(companyInfo == null) {
			throw new IllegalArgumentException("해당 업체가 존재하지 않습니다.");
		}

		//회사와 허브 ID를 요청 DTO에 설정
		UUID companyId = companyInfo.getCompanyId();
		UUID hubId = companyInfo.getHubId();
		requestDto.setCompanyId(companyId);
		requestDto.setHubId(hubId);*/

		UUID SampleHubId = UUID.fromString("2ef2fc8c-1f38-40f6-990d-13fdd9a2f59d");
		CompanyResponseDto companyInfo = CompanyResponseDto.builder()
			.companyId(UUID.randomUUID())
			.hubId(SampleHubId)
			.build();
		UUID companyId = companyInfo.getCompanyId();
		UUID hubId = companyInfo.getHubId();
		requestDto.setCompanyId(companyId);
		requestDto.setHubId(hubId);

		//상품 등록
		Product product = productMapper.createToProduct(requestDto);
		Product savedProduct = productRepository.save(product);

		return productMapper.productToResponse(savedProduct);
	}

	//상품 등록(마스터)
	@Transactional
	public ProductResponseInternalDto createMasterProduct(ProductCreateInternalDto requestDto) {
		//FeignClient
		// CompanyResponseDto companyInfo = companyServiceClient.getCompanyById(userId);
		CompanyResponseDto companyInfo = CompanyResponseDto.builder()
			.hubId(UUID.randomUUID())
			.build();
		UUID hubId = companyInfo.getHubId();

		requestDto.setHubId(hubId);

		//상품 정보 등록
		Product product = productMapper.createToProduct(requestDto);
		Product savedProduct = productRepository.save(product);

		return productMapper.productToResponse(savedProduct);
	}
	
	//상품 수정
	@Transactional
	public ProductResponseInternalDto updateProduct(
		// Long userId,
		// String userRole,
		UUID productId,
		ProductUpdateInternalDto requestDto
	) {
		//수정할 상품 정보
		Product product = productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
		UUID companyId = product.getCompanyId();
		UUID hubId = product.getHubId();
		
		//TODO 수정 권한 체크 (본인 업체, 담당 허브)
		//productId를 가지고 해당 상품 검색 > 업체, 허브가 사용자와 맞는지 체크
		/*if(userRole.equals("HUB")) {
			HubResponseDto hubInfo = hubFeignClient.getHubInfo(hubId);
			if(!hubInfo.getHubId().equals(hubId)) {
				throw new IllegalArgumentException("담당 허브 상품만 수정이 가능합니다.");
			}

		}else if(userRole.equals("COMPANY")) {
			CompanyResponseDto companyInfo = companyServiceClient.getCompanyById(userId);
			if(!companyInfo.getCompanyId().equals(companyId)) {
				throw new IllegalArgumentException("본인 업체의 상품만 수정이 가능합니다.");
			}
		}*/

		if(!product.getHubId().equals(hubId)) {
			throw new IllegalArgumentException("상품을 수정할 권한이 없습니다.");
		}else if(!product.getCompanyId().equals(companyId)) {
				throw new IllegalArgumentException("상품을 수정할 권한이 없습니다.");
		}

		//상품 정보 수정
		productMapper.updateToProduct(requestDto, product);
		return productMapper.productToResponse(product);
	}
	
	//상품 삭제
	@Transactional
	public void deleteProduct(/*Long userId, String userRole,*/ UUID productId) {
		Product product = productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

		//TODO 삭제 권한이 있는 사람인 지 체크(마스터, 담당 허브)
		//UUID hubId = product.getHubId();
		/*if(userRole.equals("HUB")) {
			HubResponseDto hubInfo = hubFeignClient.getHubInfo(hubId);
			if(!hubInfo.getHubId().equals(hubId)) {
				throw new IllegalArgumentException("담당 허브 상품만 삭제가 가능합니다.");
			}
		}*/

		UUID SampleHubId = UUID.fromString("2ef2fc8c-1f38-40f6-990d-13fdd9a2f59d");
		CompanyResponseDto companyInfo = CompanyResponseDto.builder()
			.hubId(SampleHubId)
			.build();
		UUID hubId = companyInfo.getHubId();

		if(!product.getHubId().equals(hubId)) {
			throw new IllegalArgumentException("상품을 수정할 권한이 없습니다.");
		}

		//상품 삭제 (soft delete)
		product.setDeleted(true);

		log.info("deleteProduct: {}", product);
	}

	//상품 목록 조회
	public Page<ProductResponseInternalDto> getProductSearch(
		// Long userId,
		// String userRole,
		String keyword,
		Pageable pageable,
		SortBy sortBy
	) {
		//FeignClient
		//HubResponseDto hubInfo = hubFeignClient.getHubInfo(hubId);
		//허브는 자신의 허브 상품만 조회 가능
		/*if ("HUB".equals(userRole)) {
			UUID hubId = hubInfo.getHubId();
			Page<Product> productList = productRepository.getProductSearchForHub(keyword, pageable, sortBy, hubId);
			return productMapper.productListToResponse(productList);
		}*/
		
		//허브 외 권한은 모든 상품 조회
		Page<Product> productList = productRepository.getProductSearch(keyword, pageable, sortBy);
		return productMapper.productListToResponse(productList);
	}

	//특정 상품 조회
	public ProductResponseInternalDto getProductDetails(UUID productId) {
		Product product = productRepository.findByProductId(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
		return productMapper.productToResponse(product);
	}
}
