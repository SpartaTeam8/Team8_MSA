package com.teamsparta8.productservice.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamsparta8.productservice.application.dtos.ProductRequestDto;
import com.teamsparta8.productservice.application.dtos.ProductResponseDto;
import com.teamsparta8.productservice.common.CommonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductContoller {

	//상품 생성
	@PostMapping("/products")
	public CommonResponse<ProductResponseDto> getProducts(
		@RequestBody ProductRequestDto requestDto
	) {

		//productId를 임시로 UUID 생성
		UUID productId = UUID.randomUUID();

		//ProductResponseDto로 변환
		ProductResponseDto responseDto = ProductResponseDto.builder()
			.productId(productId)
			.productName(requestDto.getProductName())
			.price(requestDto.getPrice())
			.isDeleted(false)
			.build();

		return CommonResponse.OK(responseDto, "상품 생성 완료");
	}
	
	//상품 수정
	@PutMapping("/products/{id}")
	public CommonResponse<ProductResponseDto> updateProducts(
		@PathVariable UUID id,
		@RequestBody ProductRequestDto requestDto
	) {
		//ProductResponseDto로 변환
		ProductResponseDto responseDto = ProductResponseDto.builder()
			.productId(id)
			.productName(requestDto.getProductName())
			.price(requestDto.getPrice())
			.isDeleted(false)
			.build();

		return CommonResponse.OK(responseDto, "상품 정보 수정 완료");
	}

	//상품 삭제
	@DeleteMapping("/products/{id}")
	public CommonResponse<Void> deleteProducts(
		@PathVariable UUID id
	) {

		return CommonResponse.OK("상품 삭제 완료");
	}
	
	//상품 목록 조회
	@GetMapping("/products")
	public CommonResponse<List<ProductResponseDto>> getProducts() {

		List<ProductResponseDto> products = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			products.add(ProductResponseDto.builder()
				.productId(UUID.randomUUID())
				.productName("상품 " + i)
				.price(1000 * i)
				.build());
		}

		return CommonResponse.OK(products, "상품 목록 조회 완료");
	}

	//특정 상품 조회
	@GetMapping("/products/{id}")
	public CommonResponse<ProductResponseDto> getProduct(
		@PathVariable UUID id
	) {
		return CommonResponse.OK(ProductResponseDto.builder()
			.productId(id)
			.productName("상품 1")
			.price(1000)
			.build(), "특정 상품 조회 완료");
	}

}
