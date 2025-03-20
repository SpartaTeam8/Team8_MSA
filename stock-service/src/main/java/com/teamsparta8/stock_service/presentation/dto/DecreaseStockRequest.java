package com.teamsparta8.stock_service.presentation.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DecreaseStockRequest {
	private UUID productId;
	private UUID hubId;
	private int quantity;
}
