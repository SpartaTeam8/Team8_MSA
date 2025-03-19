package com.teamsparta8.productservice.presentation.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReadDto {

	private UUID productId;
	private String productName;
	private int price;
	private boolean isDeleted;
}
