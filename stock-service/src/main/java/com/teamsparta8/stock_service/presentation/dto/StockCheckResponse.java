package com.teamsparta8.stock_service.presentation.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//stockservice -> orderservice 반환하는 데이터
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockCheckResponse {
	private UUID productId;
	private UUID hubId;
	private int price;
}
