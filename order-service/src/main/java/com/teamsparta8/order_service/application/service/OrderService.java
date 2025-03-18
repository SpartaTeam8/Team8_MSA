package com.teamsparta8.order_service.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.teamsparta8.order_service.infrastructure.repository.OrderQueryRepository;
import com.teamsparta8.order_service.infrastructure.util.PaginationUtil;
import com.teamsparta8.order_service.presentatin.dto.CommonResponse;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderResponse;
import com.teamsparta8.order_service.application.dto.OrderMapper;
import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.domain.repository.OrderRepository;
import com.teamsparta8.order_service.domain.service.OrderDomainService;
import com.teamsparta8.order_service.presentatin.dto.OrderPageResponse;
import com.teamsparta8.order_service.presentatin.dto.Pagination;
import com.teamsparta8.order_service.presentatin.dto.UpdateOrderRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderDomainService orderDomainService;
	private final OrderMapper orderMapper;
	private final OrderQueryRepository orderQueryRepository;
	@Transactional
	public CreateOrderResponse createOrder(CreateOrderRequest request) {
		UUID hubId = UUID.randomUUID();
		UUID deliveryId = UUID.randomUUID();
		// 도메인 서비스 호출 (비즈니스 로직 위임)
		Order order = orderDomainService.createOrder(
			request.getSupplierCompanyId(),
			request.getReceiverCompanyId(),
			request.getProductId(),
			hubId,
			deliveryId,
			request.getQuantity(),
			request.getRequestDescription()
		);


		orderRepository.save(order); //JPA에서 persist()가 실행되도록 보장

		return orderMapper.fromEntity(order);
	}

	@Transactional
	public CreateOrderResponse updateOrder(UUID orderId, UpdateOrderRequest request) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));

		Order updatedOrder = orderDomainService.updateOrder(order, request.getQuantity(), request.getRequestDescription());

		orderRepository.save(updatedOrder);

		return orderMapper.fromEntity(updatedOrder);
	}
	//단건 조회
	public CreateOrderResponse getOrderById(UUID orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
		return orderMapper.fromEntity(order); //
	}

	//목록 조회
	@Transactional(readOnly = true)
	public OrderPageResponse getUserOrders(UUID userId, int page, int size) {
		QueryResults<Order> queryResults = orderQueryRepository.findOrdersByUserId(userId, page, size);
		List<CreateOrderResponse> response = PaginationUtil.mapResults(queryResults, orderMapper::fromEntity);
		Pagination pagination = PaginationUtil.createPagination(queryResults, page, size);
		return new OrderPageResponse(response, pagination);
	}

	@Transactional(readOnly = true)
	public OrderPageResponse getOrdersByHub(UUID hubId, int page, int size) {
		QueryResults<Order> queryResults = orderQueryRepository.findOrdersByHubId(hubId, page, size);
		List<CreateOrderResponse> response = PaginationUtil.mapResults(queryResults, orderMapper::fromEntity);
		Pagination pagination = PaginationUtil.createPagination(queryResults, page, size);
		return new OrderPageResponse(response, pagination);
	}

	@Transactional(readOnly = true)
	public OrderPageResponse getOrdersByDeliveryId(UUID deliveryId, int page, int size) {
		QueryResults<Order> queryResults = orderQueryRepository.findOrdersByDeliveryId(deliveryId, page, size);
		List<CreateOrderResponse> response = PaginationUtil.mapResults(queryResults, orderMapper::fromEntity);
		Pagination pagination = PaginationUtil.createPagination(queryResults, page, size);
		return new OrderPageResponse(response, pagination);
	}

	public void deleteOrder(UUID orderId) {
		orderRepository.deleteById(orderId);
	}


}
