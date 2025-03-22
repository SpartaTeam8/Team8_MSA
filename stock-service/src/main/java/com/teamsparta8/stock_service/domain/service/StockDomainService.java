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
	public Stock createStock(UUID productId, UUID hubId, int quantity,int price) {
		Stock stock = Stock.builder()
			.productId(productId)
			.hubId(hubId)
			.quantity(quantity)
			.price(price)
			.build();

		return stockRepository.save(stock);
	}


	// `productId` 기반으로 Stock 조회
	public Stock findStockByProduct(UUID productId) {
		return stockRepository.findFirstByProductId(productId)
			.orElseThrow(() -> new RuntimeException("해당 상품의 재고가 없습니다."));
	}

	// 재고 검증 로직
	public void validateStock(Stock stock, int quantity) {
		if (stock.getQuantity() < quantity) {
			throw new RuntimeException("재고가 부족합니다.");
		}
	}

	// 재고 차감 및 저장 (JPA 변경 감지를 위해 저장)
	@Transactional
	public void decreaseStock(Stock stock, int quantity) {
		stock.decreaseStock(quantity);
		stockRepository.save(stock);
	}

	//재고 수정
	@Transactional
	public Stock updateStockByProductAndHub(UUID productId, UUID hubId, int newQuantity) {
		Stock stock = stockRepository.findByHubIdAndProductId(hubId, productId)
			.orElseThrow(() -> new RuntimeException("해당 허브에서 상품을 찾을 수 없습니다."));

		stock.updateStock(newQuantity);
		return stockRepository.save(stock);
	}
	@Transactional
	public void deleteStock(UUID stockId) {
		stockRepository.deleteByStockId(stockId);
	}

	@Transactional
	public void increaseStock(Stock stock, int quantity) {
		stock.increaseStock(quantity);
		stockRepository.save(stock);
	}

	//유효성 체크
	public void rollbackStock(Stock stock, int quantity) {
		// 실제 재고가 적다면 더해주기 전에 검증
		if (quantity < 0) throw new IllegalArgumentException("복구 수량은 0보다 커야 합니다.");
		stock.increaseStock(quantity);
		stockRepository.save(stock);
	}


}
