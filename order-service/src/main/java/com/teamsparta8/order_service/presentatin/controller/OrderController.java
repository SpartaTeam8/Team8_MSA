package com.teamsparta8.order_service.presentatin.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
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

import com.teamsparta8.order_service.presentatin.dto.CreateOrderRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderResponse;
import com.teamsparta8.order_service.application.service.OrderService;
import com.teamsparta8.order_service.presentatin.dto.CommonResponse;
import com.teamsparta8.order_service.presentatin.dto.OrderPageResponse;
import com.teamsparta8.order_service.presentatin.dto.Pagination;
import com.teamsparta8.order_service.presentatin.dto.UpdateOrderRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	// 주문 생성
	@PostMapping
	public ResponseEntity<CommonResponse<CreateOrderResponse>> createOrder(@RequestBody @Valid CreateOrderRequest request) {
		CreateOrderResponse response = orderService.createOrder(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(CommonResponse.OK(response, "주문이 성공적으로 생성되었습니다."));
	}
	//  주문 수정
	@PutMapping("/{orderId}")
	public ResponseEntity<CommonResponse<CreateOrderResponse>> updateOrder(
		@PathVariable UUID orderId,
		@RequestBody @Valid UpdateOrderRequest request
	) {
		CreateOrderResponse response = orderService.updateOrder(orderId, request);
		return ResponseEntity.ok(CommonResponse.OK(response, "주문이 성공적으로 수정되었습니다."));
	}


	// 주문 단일 조회
	@GetMapping("/{orderId}")
	public ResponseEntity<CommonResponse<CreateOrderResponse>> getOrderById(@PathVariable UUID orderId) {
		CreateOrderResponse order = orderService.getOrderById(orderId);
		return ResponseEntity.ok(CommonResponse.OK(order, "주문 조회 성공"));
	}
	// 주문 삭제
	@DeleteMapping("/{orderId}")
	public ResponseEntity<CommonResponse<Void>> deleteOrder(@PathVariable UUID orderId) {
		orderService.deleteOrder(orderId);
		return ResponseEntity.ok(CommonResponse.OK("주문이 삭제되었습니다."));
	}
	// 주문 검색 API (다양한 조건 조합 가능)
	@GetMapping("/search")
	public ResponseEntity<CommonResponse<List<CreateOrderResponse>>> searchOrders(
		@RequestParam(required = false) UUID userId,
		@RequestParam(required = false) UUID hubId,
		@RequestParam(required = false) UUID deliveryId,
		@RequestParam(required = false) String status,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		OrderPageResponse orderPageResponse = orderService.searchOrders(userId, hubId, deliveryId, status, page, size);
		return ResponseEntity.ok(
			CommonResponse.OK(orderPageResponse.getOrders(), "주문 검색 결과 조회 성공", orderPageResponse.getPagination())
		);
	}
	//주문 조회
	@GetMapping
	public ResponseEntity<CommonResponse<OrderPageResponse>> getAllOrders(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		OrderPageResponse response = orderService.getAllOrders(page, size);
		return ResponseEntity.ok(CommonResponse.OK(response, "주문 전체 목록 조회 성공"));
	}

}
