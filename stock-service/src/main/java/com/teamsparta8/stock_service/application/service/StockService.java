package com.teamsparta8.stock_service.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsparta8.stock_service.application.dto.StockMapper;
import com.teamsparta8.stock_service.domain.model.Stock;
import com.teamsparta8.stock_service.domain.service.StockDomainService;
import com.teamsparta8.stock_service.presentation.dto.CreateStockRequest;
import com.teamsparta8.stock_service.presentation.dto.CreateStockResponse;
import com.teamsparta8.stock_service.presentation.dto.DecreaseStockRequest;
import com.teamsparta8.stock_service.presentation.dto.RollbackStockRequest;
import com.teamsparta8.stock_service.presentation.dto.StockCheckResponse;
import com.teamsparta8.stock_service.presentation.dto.UpdateStockResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	private final StockDomainService stockDomainService;
	// private final ProductClient productClient;
	private final StockMapper stockMapper;
	private static final int DUMMY_PRICE = 10000;

	//재고 생성
	@Transactional
	public CreateStockResponse createStock(CreateStockRequest request) {
		//  ProductService에서 상품 정보 가져오기
		// ProductResponse product = productClient.getProductDetails(request.getProductId());
		//
		// if (product == null) {
		// 	throw new IllegalArgumentException("존재하지 않는 상품입니다.");
		// }
		// UUID huubId = prodcut.getHubId();
		UUID dummyHubId = UUID.randomUUID();
		// int Price = 1000;
		//  ProductService에서 받은 hubId 사용
		Stock stock = stockDomainService.createStock(request.getProductId(), dummyHubId, request.getQuantity(),
			request.getPrice());

		return stockMapper.fromEntity(stock);
	}

	@Transactional
	public StockCheckResponse checkAndDecreaseStock(DecreaseStockRequest request){
		// `productId` 기반으로 hubId 포함한 Stock 조회 (StockDomainService 호출)
		Stock stock = stockDomainService.findStockByProduct(request.getProductId());

		// 재고 확인 (StockDomainService에서 비즈니스 로직 수행)
		stockDomainService.validateStock(stock, request.getQuantity());

		// 재고 차감 (StockDomainService에서 처리)
		stockDomainService.decreaseStock(stock, request.getQuantity());

		// Stock 테이블에서 가격 가져오기
		int price = stock.getPrice();

		// 차감된 재고 정보 반환
		return StockCheckResponse.builder()
			.productId(stock.getProductId())
			.hubId(stock.getHubId()) // StockDomainService에서 조회한 hubId
			.price(price)
			.build();
	}

	//복원 로직
	@Transactional
	public void rollbackStock(UUID productId, int quantity) {
		Stock stock = findStockByProduct(productId); // 이제 오류 안 뜸!
		stock.increaseStock(quantity);
	}
	@Transactional(readOnly = true)
	public Stock findStockByProduct(UUID productId) {
		return stockDomainService.findStockByProduct(productId);
	}
	//재고 조회
	@Transactional(readOnly = true)
	public CreateStockResponse getStockByProductId(UUID productId) {
		Stock stock = stockDomainService.findStockByProduct(productId);
		return stockMapper.fromEntity(stock);
	}

	@Transactional
	public UpdateStockResponse updateStock(UUID productId, UUID hubId, int newQuantity) {
		// 도메인 서비스 호출 (재고 조회 및 수정)
		Stock stock = stockDomainService.updateStockByProductAndHub(productId, hubId, newQuantity);
		return stockMapper.toUpdateResponse(stock);
	}


	@Transactional
	public void deleteStock(UUID stockId) {
		stockDomainService.deleteStock(stockId);
	}
}
