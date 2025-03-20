package com.teamsparta8.stock_service.presentation.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateStockRequest {
	private UUID stockId;
	private UUID hubId;

	private int newQuantity;
}
