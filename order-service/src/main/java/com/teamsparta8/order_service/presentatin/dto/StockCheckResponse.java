package com.teamsparta8.order_service.presentatin.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCheckResponse {
	private UUID productId;
	private UUID hubId;
	private int price;
}
