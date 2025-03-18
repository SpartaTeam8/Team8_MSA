package com.teamsparta8.productservice.presentation.util;

import org.springframework.data.domain.Page;

import com.teamsparta8.productservice.application.dtos.ProductCreateInternalDto;
import com.teamsparta8.productservice.application.dtos.ProductResponseInternalDto;
import com.teamsparta8.productservice.application.dtos.ProductUpdateInternalDto;
import com.teamsparta8.productservice.presentation.dto.ProductCreateDto;
import com.teamsparta8.productservice.presentation.dto.ProductResponseDto;
import com.teamsparta8.productservice.presentation.dto.ProductUpdateDto;

public class ProductDtoMapper {

	public static ProductCreateInternalDto convertToCreateInternalDto(ProductCreateDto createDto) {

		return ProductCreateInternalDto.builder()
			.companyId(createDto.getCompanyId())
			.hubId(createDto.getHubId())
			.productName(createDto.getProductName())
			.price(createDto.getPrice())
			.build();
	}

	public static ProductUpdateInternalDto convertToUpdateInternalDto(ProductUpdateDto requestDto) {
		return ProductUpdateInternalDto.builder()
			.productName(requestDto.getProductName())
			.price(requestDto.getPrice())
			.build();
	}

	public static ProductResponseDto convertToResponseDto(ProductResponseInternalDto response) {
		return ProductResponseDto.builder()
			.productId(response.getProductId())
			.productName(response.getProductName())
			.price(response.getPrice())
			.isDeleted(response.isDeleted())
			.build();
	}

	public static Page<ProductResponseDto> convertToResponseList(Page<ProductResponseInternalDto> productList) {
		return productList.map(ProductDtoMapper::convertToResponseDto);
	}
}
