package com.teamsparta8.stock_service.infrastructure.client;

import java.util.UUID;

import lombok.Getter;

@Getter
public class ProductResponse {
	private UUID productId;
	private UUID hubId;
	private int price;
}
