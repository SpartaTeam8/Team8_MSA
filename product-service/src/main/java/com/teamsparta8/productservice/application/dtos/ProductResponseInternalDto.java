package com.teamsparta8.productservice.application.dtos;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseInternalDto {

	private UUID productId;
	private UUID companyId;
	private UUID hubId;
	private String productName;
	private int price;
	private boolean isDeleted;
}
