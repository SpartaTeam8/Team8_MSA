package com.teamsparta8.productservice.presentation.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class ProductMasterCreateDto {

	private UUID companyId;
	private String productName;
	private int price;
}
