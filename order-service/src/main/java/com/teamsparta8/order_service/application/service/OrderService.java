package com.teamsparta8.order_service.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsparta8.order_service.presentatin.dto.CreateOrderRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderResponse;
import com.teamsparta8.order_service.application.dto.OrderMapper;
import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.domain.repository.OrderRepository;
import com.teamsparta8.order_service.domain.service.OrderDomainService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderDomainService orderDomainService;
	private final OrderMapper orderMapper;
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

	public Page<CreateOrderResponse> getAllOrders(int page, int size) {
		return null;
	}

	public CreateOrderResponse getOrderById(UUID orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
		return orderMapper.fromEntity(order); //  Mapper를 사용하여 변환
	}

	public void deleteOrder(UUID orderId) {
		orderRepository.deleteById(orderId);
	}
}
