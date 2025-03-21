package com.teamsparta8.order_service.infrastructure.client;

import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.teamsparta8.order_service.presentatin.dto.DecreaseStockRequest;
import com.teamsparta8.order_service.presentatin.dto.StockCheckResponse;

@Component
@Profile("test")
public class MockStockClient implements StockClient{

	@Override
	public StockCheckResponse checkAndDecreaseStock(DecreaseStockRequest request) {
		return StockCheckResponse.builder()
			.productId(request.getProductId())
			.hubId(UUID.fromString("11111111-1111-1111-1111-111111111111")) // 고정된 허브 ID
			.price(10000)
			.build();
	}
}
