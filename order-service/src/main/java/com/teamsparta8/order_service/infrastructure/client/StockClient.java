package com.teamsparta8.order_service.infrastructure.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.teamsparta8.order_service.presentatin.dto.DecreaseStockRequest;
import com.teamsparta8.order_service.presentatin.dto.StockCheckResponse;
@FeignClient(name = "stock-service", url = "http://localhost:8081", fallback = StockClient.DummyStockClient.class)
public interface StockClient {
	@PutMapping("/api/stocks/check")
	StockCheckResponse checkAndDecreaseStock(@RequestBody DecreaseStockRequest request);


	@Component
	public class DummyStockClient implements StockClient {
		@Override
		public StockCheckResponse checkAndDecreaseStock(DecreaseStockRequest request) {
			return StockCheckResponse.builder()
				.productId(request.getProductId())
				.hubId(UUID.randomUUID())
				.price(9000)
				.build();
		}
	}
}
