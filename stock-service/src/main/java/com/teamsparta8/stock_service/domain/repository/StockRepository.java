package com.teamsparta8.stock_service.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.teamsparta8.stock_service.domain.model.Stock;

public interface StockRepository {
	Optional<Stock> findFirstByProductId(UUID productId);
	Optional<Stock> findByHubIdAndProductId(UUID hubId, UUID productId);
	Stock save(Stock stock);
	void deleteByStockId(UUID stockId);
}
