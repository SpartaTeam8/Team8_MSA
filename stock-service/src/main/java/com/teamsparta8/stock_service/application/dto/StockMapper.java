package com.teamsparta8.stock_service.application.dto;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.teamsparta8.stock_service.domain.model.Stock;
import com.teamsparta8.stock_service.presentation.dto.CreateStockRequest;
import com.teamsparta8.stock_service.presentation.dto.CreateStockResponse;
import com.teamsparta8.stock_service.presentation.dto.UpdateStockResponse;

@Component
public class StockMapper {
	public Stock toEntity(CreateStockRequest request, UUID stockId) {
		return Stock.builder()
			.stockId(stockId)
			.hubId(request.getHubId())
			.productId(request.getProductId())
			.quantity(request.getQuantity())
			.build();
	}

	public CreateStockResponse fromEntity(Stock stock) {
		return CreateStockResponse.builder()
			.stockId(stock.getStockId())
			.hubId(stock.getHubId())
			.productId(stock.getProductId())
			.quantity(stock.getQuantity())
			.build();

	}
	public UpdateStockResponse toUpdateResponse(Stock stock) {
		return UpdateStockResponse.builder()
			.stockId(stock.getStockId())
			.hubId(stock.getHubId())
			.productId(stock.getProductId())
			.updatedQuantity(stock.getQuantity())
			.build();
	}
}
