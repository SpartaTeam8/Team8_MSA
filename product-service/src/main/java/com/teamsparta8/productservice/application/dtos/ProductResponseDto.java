package com.teamsparta8.productservice.application.dtos;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDto {

	private UUID productId;
	private String productName;
	private int price;
	private boolean isDeleted;
}
