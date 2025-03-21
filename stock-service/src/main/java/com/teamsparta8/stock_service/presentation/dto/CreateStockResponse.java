package com.teamsparta8.stock_service.presentation.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateStockResponse {
	private UUID stockId;
	private UUID hubId;
	private UUID productId;
	private int quantity;
}
