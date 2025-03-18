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

	// [일반 사용자] 본인이 주문한 목록 조회
	@GetMapping("/my")
	public ResponseEntity<CommonResponse<List<CreateOrderResponse>>> getMyOrders(
		@RequestParam UUID userId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		OrderPageResponse orderPageResponse = orderService.getUserOrders(userId, page, size);
		return ResponseEntity.ok(
			CommonResponse.OK(orderPageResponse.getOrders(), "사용자 주문 목록 조회 성공", orderPageResponse.getPagination())
		);
	}

	// [허브 관리자] 본인의 허브에서 관리하는 주문 목록 조회
	@GetMapping("/hub")
	public ResponseEntity<CommonResponse<List<CreateOrderResponse>>> getHubOrders(
		@RequestParam String role,
		@RequestParam UUID hubId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		if (!role.equals("HUB_MANAGER")) {
			return ResponseEntity.badRequest().body(CommonResponse.ERROR("허브 관리자만 접근 가능합니다."));
		}

		OrderPageResponse orderPageResponse = orderService.getOrdersByHub(hubId, page, size);
		return ResponseEntity.ok(
			CommonResponse.OK(orderPageResponse.getOrders(), "허브 관리자 주문 목록 조회 성공", orderPageResponse.getPagination())
		);
	}

	//[배송 담당자] 본인이 담당하는 주문 목록 조회
	@GetMapping("/delivery")
	public ResponseEntity<CommonResponse<List<CreateOrderResponse>>> getDeliveryOrders(
		@RequestParam String role,
		@RequestParam UUID userId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		if (!role.equals("DELIVERY_PERSON")) {
			return ResponseEntity.badRequest().body(CommonResponse.ERROR("배송 담당자만 접근 가능합니다."));
		}

		OrderPageResponse orderPageResponse = orderService.getOrdersByDeliveryId(userId, page, size);
		return ResponseEntity.ok(
			CommonResponse.OK(orderPageResponse.getOrders(), "배송 담당자 주문 목록 조회 성공", orderPageResponse.getPagination())
		);
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
