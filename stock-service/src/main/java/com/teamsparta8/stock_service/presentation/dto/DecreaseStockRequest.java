package com.teamsparta8.stock_service.presentation.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//orderservice -> stockservice로 보내는 데이터
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseStockRequest {
	private UUID productId;
	private int quantity;
}
