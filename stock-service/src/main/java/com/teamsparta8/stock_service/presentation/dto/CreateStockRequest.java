package com.teamsparta8.stock_service.presentation.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateStockRequest {
	private UUID hubId;
	private UUID productId;
	private int quantity;

}
