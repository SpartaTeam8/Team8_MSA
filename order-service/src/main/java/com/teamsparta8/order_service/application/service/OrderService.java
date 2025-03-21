package com.teamsparta8.order_service.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.teamsparta8.order_service.infrastructure.client.DeliveryClient;
import com.teamsparta8.order_service.infrastructure.client.StockClient;
import com.teamsparta8.order_service.infrastructure.repository.OrderQueryRepository;
import com.teamsparta8.order_service.infrastructure.util.PaginationUtil;
import com.teamsparta8.order_service.presentatin.dto.CommonResponse;
import com.teamsparta8.order_service.presentatin.dto.CreateDeliveryRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateDeliveryResponse;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderResponse;
import com.teamsparta8.order_service.application.dto.OrderMapper;
import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.domain.repository.OrderRepository;
import com.teamsparta8.order_service.domain.service.OrderDomainService;
import com.teamsparta8.order_service.presentatin.dto.DecreaseStockRequest;
import com.teamsparta8.order_service.presentatin.dto.OrderPageResponse;
import com.teamsparta8.order_service.presentatin.dto.Pagination;
import com.teamsparta8.order_service.presentatin.dto.StockCheckResponse;
import com.teamsparta8.order_service.presentatin.dto.UpdateOrderRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderDomainService orderDomainService;
	private final OrderMapper orderMapper;
	private final OrderQueryRepository orderQueryRepository;
	private final StockClient stockClient;
	private final DeliveryClient deliveryClient;
	@Transactional
	public CreateOrderResponse createOrder(CreateOrderRequest request) {
		// 1. Stock Service를 통한 재고 확인 및 차감
		DecreaseStockRequest stockRequest = new DecreaseStockRequest(request.getProductId(), request.getQuantity());
		StockCheckResponse stockResponse;
		try {
			stockResponse = stockClient.checkAndDecreaseStock(stockRequest);
		} catch (Exception e) {
			throw new RuntimeException("재고 확인 및 차감 실패: " + e.getMessage());
		}

		int totalPrice = stockResponse.getPrice() * request.getQuantity();

		// 2. 도메인 서비스 호출하여 Order 생성
		Order order = orderDomainService.createOrder(
			request.getSupplierCompanyId(),
			request.getReceiverCompanyId(),
			request.getProductId(),
			stockResponse.getHubId(),
			null,
			request.getQuantity(),
			totalPrice,
			request.getRequestDescription()
		);
		// 3. 주문 저장 (deliveryId 없이 우선 저장)
		orderDomainService.saveOrder(order);

		// 4. Delivery 서비스 호출
		CreateDeliveryRequest deliveryRequest = new CreateDeliveryRequest(order.getOrderId());
		CreateDeliveryResponse deliveryResponse = deliveryClient.createDelivery(deliveryRequest);

		// 5. 받은 deliveryId로 주문 정보 업데이트
		orderDomainService.updateDeliveryId(order, deliveryResponse.getDeliveryId());

		// 6. 응답 변환 및 반환
		return orderMapper.fromEntity(order);
	}




	@Transactional
	public CreateOrderResponse updateOrder(UUID orderId, UpdateOrderRequest request) {
		Order order = orderDomainService.getOrderById(orderId);
		Order updated = orderDomainService.updateOrder(order, request.getQuantity(), request.getRequestDescription());
		orderDomainService.saveOrder(updated);
		return orderMapper.fromEntity(updated);
	}

	//단건 조회

	@Transactional(readOnly = true)
	public CreateOrderResponse getOrderById(UUID orderId) {
		Order order = orderDomainService.getOrderById(orderId);
		return orderMapper.fromEntity(order);
	}

	@Transactional
	public void deleteOrder(UUID orderId) {
		orderDomainService.deleteOrder(orderId);
	}
	//목록 조회
	@Transactional(readOnly = true)
	public OrderPageResponse getAllOrders(int page, int size) {
		QueryResults<Order> queryResults = orderDomainService.getAllOrders(page, size);
		List<CreateOrderResponse> response = PaginationUtil.mapResults(queryResults, orderMapper::fromEntity);
		Pagination pagination = PaginationUtil.createPagination(queryResults, page, size);
		return new OrderPageResponse(response, pagination);
	}
	@Transactional(readOnly = true)
	public OrderPageResponse searchOrders(UUID userId, UUID hubId, UUID deliveryId, String status, int page, int size) {
		QueryResults<Order> queryResults = orderQueryRepository.searchOrders(userId, hubId, deliveryId, status, page, size);
		List<CreateOrderResponse> response = PaginationUtil.mapResults(queryResults, orderMapper::fromEntity);
		Pagination pagination = PaginationUtil.createPagination(queryResults, page, size);
		return new OrderPageResponse(response, pagination);
	}



}
