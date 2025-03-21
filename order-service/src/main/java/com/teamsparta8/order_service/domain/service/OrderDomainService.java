package com.teamsparta8.order_service.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.domain.repository.OrderRepository;
import com.teamsparta8.order_service.infrastructure.repository.OrderQueryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDomainService {

	private final OrderRepository orderRepository;
	private final OrderQueryRepository orderQueryRepository;

	// 주문 생성 시 비즈니스 규칙 적용
	public Order createOrder(UUID supplierCompanyId, UUID receiverCompanyId, UUID productId, UUID hubId, UUID deliveryId, int quantity, int totalPrice, String requestDescription
	) {

		if (quantity <= 0) {
			throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
		}

		Order order = Order.builder()
			.supplierCompanyId(supplierCompanyId)
			.receiverCompanyId(receiverCompanyId)
			.productId(productId)
			.hubId(hubId)
			.deliveryId(deliveryId)
			.quantity(quantity)
			.totalPrice(totalPrice)
			.requestDescription(requestDescription)
			.build();

		return orderRepository.save(order);
	}

	public Order updateOrder(Order order, int quantity, String requestDescription) {
		//  수량 검증
		if (quantity <= 0) {
			throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");
		}

		//  변경된 데이터 반영 (불변성 유지)
		return Order.builder()
			.orderId(order.getOrderId())
			.supplierCompanyId(order.getSupplierCompanyId())
			.receiverCompanyId(order.getReceiverCompanyId())
			.productId(order.getProductId())
			.hubId(order.getHubId())
			.deliveryId(order.getDeliveryId())
			.quantity(quantity)
			.totalPrice(order.getTotalPrice())
			.requestDescription(requestDescription)
			.build();
	}

	@Transactional(readOnly = true)
	public QueryResults<Order> getAllOrders(int page, int size) {
		return orderQueryRepository.findAllOrders(page, size);
	}
	public Order getOrderById(UUID orderId) {
		return orderRepository.findById(orderId)
			.orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
	}

	public void deleteOrder(UUID orderId) {
		orderRepository.deleteById(orderId);
	}
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public void updateDeliveryId(Order order, UUID deliveryId) {
		order.updateDeliveryId(deliveryId);
		orderRepository.save(order); // JPA dirty checking 보장용
	}
}
