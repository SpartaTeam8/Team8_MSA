package com.teamsparta8.order_service.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teamsparta8.order_service.domain.model.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDomainService {

	// 주문 생성 시 비즈니스 규칙 적용
	public Order createOrder(
		UUID orderId,
		UUID supplierCompanyId,
		UUID receiverCompanyId,
		UUID productId,
		UUID hubId,
		UUID deliveryId,
		int quantity,
		String requestDescription
	) {
		// 주문 생성 규칙 예시 (비즈니스 로직)
		if (quantity <= 0) {
			throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
		}

		return Order.builder()
			.orderId(orderId)
			.supplierCompanyId(supplierCompanyId)
			.receiverCompanyId(receiverCompanyId)
			.productId(productId)
			.hubId(hubId)
			.deliveryId(deliveryId)
			.quantity(quantity)
			.requestDescription(requestDescription)
			.build();
	}
}
