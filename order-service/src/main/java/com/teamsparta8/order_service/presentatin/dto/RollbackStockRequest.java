package com.teamsparta8.order_service.presentatin.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RollbackStockRequest {
	private UUID productId;
	private int quantity;
}
