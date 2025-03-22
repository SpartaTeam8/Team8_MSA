package com.teamsparta8.stock_service.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.teamsparta8.stock_service.domain.model.Stock;
import com.teamsparta8.stock_service.domain.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {
	private final JpaStockRepository jpaStockRepository;

	@Override
	public Optional<Stock> findFirstByProductId(UUID productId) {
		return jpaStockRepository.findByProductId(productId);
	}

	@Override
	public Optional<Stock> findByHubIdAndProductId(UUID hubId, UUID productId) {
		return jpaStockRepository.findByHubIdAndProductId(hubId, productId);
	}

	@Override
	public Stock save(Stock stock) {
		return jpaStockRepository.save(stock);
	}

	@Override
	public void deleteByStockId(UUID stockId) {
		jpaStockRepository.deleteById(stockId);
	}
}
