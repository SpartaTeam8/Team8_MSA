package com.teamsparta8.stock_service.presentation.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RollbackStockRequest {
	private UUID productId;
	private int quantity;

}
