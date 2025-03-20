package com.teamsparta8.productservice.presentation.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class ProductCreateDto {

	private UUID companyId;
	private UUID hubId;
	private String productName;
	private int price;
}
