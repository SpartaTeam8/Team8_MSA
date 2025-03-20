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
import com.teamsparta8.stock_service.presentation.dto.UpdateStockRequest;
import com.teamsparta8.stock_service.presentation.dto.UpdateStockResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	private final StockDomainService stockDomainService;
	// private final ProductClient productClient;
	private final StockMapper stockMapper;


	//재고 생성
	@Transactional
	public CreateStockResponse createStock(CreateStockRequest request) {
		//  ProductService에서 상품 정보 가져오기
		// ProductResponse product = productClient.getProductDetails(request.getProductId());
		//
		// if (product == null) {
		// 	throw new IllegalArgumentException("존재하지 않는 상품입니다.");
		// }

		UUID dummyHubId = UUID.randomUUID();
		int dummyPrice = 10000;

		//  ProductService에서 받은 hubId 사용
		Stock stock = Stock.builder()
			.productId(request.getProductId())
			.hubId(dummyHubId) //  ProductService에서 가져온 hubId 대신 더미데이터
			.quantity(request.getQuantity())
			.build();

		return stockMapper.fromEntity(stock);
	}

	//재고 차감
	@Transactional
	public int decreaseStock(DecreaseStockRequest request) {
		// ProductService에서 상품 정보 가져오기
		// ProductResponse product = productClient.getProductDetails(request.getProductId());
		//
		// if (product == null) {
		// 	throw new IllegalArgumentException("존재하지 않는 상품입니다.");
		// }
		//
		// // 상품의 hubId와 요청한 hubId가 일치하는지 검증
		// if (!product.getHubId().equals(request.getHubId())) {
		// 	throw new IllegalArgumentException("해당 상품은 이 허브에서 관리되지 않습니다.");
		// }
		UUID dummyHubId = UUID.randomUUID();
		int dummyPrice = 10000;
		// 해당 허브의 재고 차감
		Stock stock = stockDomainService.findStockByProductAndHub(request.getProductId(), dummyHubId);
		stockDomainService.decreaseStock(stock, request.getQuantity());

		// 상품 가격 조회
		// int price = productClient.getProductPrice(request.getProductId());
		// if (price <= 0) {
		// 	throw new RuntimeException("상품 가격을 조회할 수 없습니다.");
		// }

		return dummyPrice;
	}

	//재고 조회
	@Transactional(readOnly = true)
	public CreateStockResponse getStockByProductId(UUID productId) {
		Stock stock = stockDomainService.findStockByProductAndHub(productId, UUID.randomUUID());
		return stockMapper.fromEntity(stock);
	}

	@Transactional
	public UpdateStockResponse updateStock(UpdateStockRequest request) {
		Stock stock = stockDomainService.findStockByProductAndHub(request.getStockId(), request.getHubId());
		stockDomainService.updateStock(stock, request.getNewQuantity());
		return UpdateStockResponse.builder()
			.stockId(stock.getStockId())
			.hubId(stock.getHubId())
			.productId(stock.getProductId())
			.updatedQuantity(stock.getQuantity())
			.build();
	}

	@Transactional
	public void deleteStock(UUID stockId) {
		stockDomainService.deleteStock(stockId);
	}
}
