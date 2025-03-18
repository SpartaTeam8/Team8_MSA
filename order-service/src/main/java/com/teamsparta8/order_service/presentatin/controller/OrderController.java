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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamsparta8.order_service.presentatin.dto.CreateOrderRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderResponse;
import com.teamsparta8.order_service.application.service.OrderService;
import com.teamsparta8.order_service.presentatin.dto.CommonResponse;
import com.teamsparta8.order_service.presentatin.dto.Pagination;

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


	// 주문 목록 조회 (Pagination 적용)
	@GetMapping
	public ResponseEntity<CommonResponse<List<CreateOrderResponse>>> getAllOrders(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		Page<CreateOrderResponse> orders = orderService.getAllOrders(page, size);
		Pagination pagination = new Pagination(
			orders.getTotalPages(),
			orders.getTotalElements(),
			orders.getNumber(),
			orders.getNumberOfElements()
		);

		return ResponseEntity.ok(CommonResponse.OK(orders.getContent(), "주문 목록 조회 성공", pagination));
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

}
