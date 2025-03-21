package com.teamsparta8.order_service.presentatin.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseStockRequest {
	private UUID productId;
	private int quantity;
}
