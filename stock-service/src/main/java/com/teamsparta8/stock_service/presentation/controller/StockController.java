package com.teamsparta8.stock_service.presentation.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamsparta8.stock_service.application.service.StockService;
import com.teamsparta8.stock_service.presentation.dto.CommonResponse;
import com.teamsparta8.stock_service.presentation.dto.CreateStockRequest;
import com.teamsparta8.stock_service.presentation.dto.CreateStockResponse;
import com.teamsparta8.stock_service.presentation.dto.DecreaseStockRequest;
import com.teamsparta8.stock_service.presentation.dto.RollbackStockRequest;
import com.teamsparta8.stock_service.presentation.dto.StockCheckResponse;
import com.teamsparta8.stock_service.presentation.dto.UpdateStockResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

	private final StockService stockService;

	// 재고 등록
	@PostMapping
	public ResponseEntity<CommonResponse<CreateStockResponse>> createStock(@RequestBody CreateStockRequest request) {
		CreateStockResponse response = stockService.createStock(request);
		return ResponseEntity.ok(CommonResponse.OK(response, "재고가 성공적으로 생성되었습니다."));
	}

	// 재고 조회
	@GetMapping("/{productId}")
	public ResponseEntity<CommonResponse<CreateStockResponse>> getStockByProductId(@PathVariable UUID productId) {
		CreateStockResponse response = stockService.getStockByProductId(productId);
		return ResponseEntity.ok(CommonResponse.OK(response, "재고 조회 성공"));
	}

	//재고차감
	@PutMapping("/check")
	public ResponseEntity<CommonResponse<StockCheckResponse>> checkAndDecreaseStock(
		@RequestBody DecreaseStockRequest request) { //@RequestBody 사용

		StockCheckResponse response = stockService.checkAndDecreaseStock(request);
		return ResponseEntity.ok(CommonResponse.OK(response, "재고 확인 및 차감 성공"));
	}


	// 재고 업데이트
	@PutMapping("/{productId}")
	public ResponseEntity<CommonResponse<UpdateStockResponse>> updateStock(
		@PathVariable UUID productId,
		@RequestParam UUID hubId,
		@RequestParam int newQuantity) {

		UpdateStockResponse response = stockService.updateStock(productId, hubId, newQuantity);
		return ResponseEntity.ok(CommonResponse.OK(response, "재고 수정 성공"));
	}
	//재고 삭제
	@DeleteMapping("/{stockId}")
	public ResponseEntity<CommonResponse<Void>> deleteStock(@PathVariable UUID stockId) {
		stockService.deleteStock(stockId);
		return ResponseEntity.ok(CommonResponse.OK("재고 삭제 성공"));
	}

	//주문 실패 시 재고 롤백
	@PutMapping("/rollback")
	public ResponseEntity<CommonResponse<String>> rollbackStock(@RequestBody RollbackStockRequest request) {
		stockService.rollbackStock(request);
		return ResponseEntity.ok(CommonResponse.OK(null, "재고 복구 성공"));
	}

}

