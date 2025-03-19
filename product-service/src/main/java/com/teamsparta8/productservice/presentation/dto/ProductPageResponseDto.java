package com.teamsparta8.productservice.presentation.dto;

import java.util.List;

import com.teamsparta8.productservice.common.Pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductPageResponseDto {

	private List<ProductResponseDto> products;
	private Pagination pagination;
}
