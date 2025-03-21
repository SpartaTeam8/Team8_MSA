package com.teamsparta8.productservice.presentation;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamsparta8.productservice.application.dtos.ProductResponseInternalDto;
import com.teamsparta8.productservice.application.service.ProductService;
import com.teamsparta8.productservice.common.CommonResponse;
import com.teamsparta8.productservice.common.Pagination;
import com.teamsparta8.productservice.domain.entity.SortBy;
import com.teamsparta8.productservice.presentation.dto.ProductCreateDto;
import com.teamsparta8.productservice.presentation.dto.ProductPageResponseDto;
import com.teamsparta8.productservice.presentation.dto.ProductResponseDto;
import com.teamsparta8.productservice.presentation.dto.ProductUpdateDto;
import com.teamsparta8.productservice.presentation.util.ProductDtoMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductContoller {

	private final ProductService productService;

	//상품 등록
	@PostMapping("/products")
	public CommonResponse<ProductResponseDto> createProduct(
		// @RequestHeader("X-client-userId") Long userId,
		// @RequestHeader("X-client-role") String userRole,
		@RequestBody ProductCreateDto requestDto
	) {
		ProductResponseInternalDto response = productService.createProduct(
			// userId,
			// userRole,
			ProductDtoMapper.convertToCreateInternalDto(requestDto)
		);
		ProductResponseDto productResponseDto = ProductDtoMapper.convertToResponseDto(response);

		return CommonResponse.OK(productResponseDto, "상품 생성 완료");
	}

	//상품 등록 (마스터)
	@PostMapping("/products/master")
	public CommonResponse<ProductResponseDto> createMasterProduct(@RequestBody ProductCreateDto requestDto) {

		ProductResponseInternalDto response = productService.createMasterProduct(
			ProductDtoMapper.convertToCreateInternalDto(requestDto)
		);
		ProductResponseDto productResponseDto = ProductDtoMapper.convertToResponseDto(response);

		return CommonResponse.OK(productResponseDto, "상품 생성 완료");
	}

	//상품 수정
	@PutMapping("/products/{productId}")
	public CommonResponse<ProductResponseDto> updateProduct(
		//@RequestHeader("X-Request-ID") Long userId,
		@PathVariable("productId") UUID productId,
		@RequestBody ProductUpdateDto requestDto
	) {
		ProductResponseInternalDto response = productService.updateProduct(
			//userId,
			productId,
			ProductDtoMapper.convertToUpdateInternalDto(requestDto)
		);
		ProductResponseDto productResponseDto = ProductDtoMapper.convertToResponseDto(response);

		return CommonResponse.OK(productResponseDto, "상품정보 수정 완료");
	}

	//상품 삭제
	@DeleteMapping("/products/{productId}")
	public CommonResponse<Void> deleteProduct(
		// @RequestHeader("X-client-userId") Long userId,
		// @RequestHeader("X-client-role") String userRole,
		@PathVariable("productId") UUID productId
	) {
		productService.deleteProduct(/*userId, userRole,*/ productId);
		return CommonResponse.OK("상품 삭제 완료");
	}
	
	//상품 목록 조회
	@GetMapping("/products")
	public CommonResponse<ProductPageResponseDto> getProductSearch(
		// @RequestHeader("X-client-userId") Long userId,
		// @RequestHeader("X-client-role") String userRole,
		@RequestParam(value = "keyword", required = false) String keyword,
		@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "size", defaultValue = "10") int size,
		@RequestParam(value = "sort", defaultValue = "CREATED") SortBy sortby
	) {
		Pageable pageable = PageRequest.of(page-1, size);
		//Page<ProductResponseInternalDto> productList = productService.getProductSearch(userId, userRole, keyword, pageable, sortby);
		Page<ProductResponseInternalDto> productList = productService.getProductSearch(keyword, pageable, sortby);
		Page<ProductResponseDto> responseList = ProductDtoMapper.convertToResponseList(productList);

		Pagination pagination = new Pagination(
			responseList.getTotalPages(),
			responseList.getTotalElements(),
			responseList.getNumber() + 1,
			responseList.getNumberOfElements()
		);

		ProductPageResponseDto response = new ProductPageResponseDto(responseList.getContent(), pagination);

		return CommonResponse.OK(response, "상품 목록 조회 완료");
	}

	//특정 상품 조회
	@GetMapping("/products/{productId}")
	public CommonResponse<ProductResponseDto> getProductDetails(@PathVariable("productId") UUID productId) {

		ProductResponseInternalDto response = productService.getProductDetails(productId);
		ProductResponseDto productResponseDto = ProductDtoMapper.convertToResponseDto(response);

		return CommonResponse.OK(productResponseDto, "상품 상세내용 조회 완료");
	}

}
