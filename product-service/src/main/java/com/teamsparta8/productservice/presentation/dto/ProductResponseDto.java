package com.teamsparta8.productservice.presentation.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

	private UUID productId;
	private String productName;
	private int price;
	private boolean isDeleted;
}
