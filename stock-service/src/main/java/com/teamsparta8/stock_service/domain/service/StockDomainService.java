package com.teamsparta8.stock_service.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsparta8.stock_service.domain.model.Stock;
import com.teamsparta8.stock_service.domain.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockDomainService {
	private final StockRepository stockRepository;

	@Transactional
	public Stock findStockByProductAndHub(UUID productId, UUID hubId) {
	// 	return stockRepository.findByHubIdAndProductId(hubId, productId)
	// 		.orElseThrow(() -> new RuntimeException("해당 허브에서 상품을 찾을 수 없습니다."));
	//
		return stockRepository.findByHubIdAndProductId(hubId, productId)
			.orElseGet(() -> new Stock(UUID.randomUUID(), productId, hubId, 100)); // 더미 재고 100개

	}

	@Transactional
	public void decreaseStock(Stock stock, int quantity) {
		stock.decreaseStock(quantity);
	}

	@Transactional
	public void updateStock(Stock stock, int newQuantity) {
		stock.updateStock(newQuantity);
	}
	@Transactional
	public void deleteStock(UUID stockId) {
		stockRepository.deleteByStockId(stockId);
	}
}
