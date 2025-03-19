package com.teamsparta8.order_service.application.dto;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderResponse;

@Component
public class OrderMapper {
	// DTO → Entity 변환
	public Order toEntity(CreateOrderRequest request,UUID hubId, UUID deliveryId) {
		return Order.builder()
			.supplierCompanyId(request.getSupplierCompanyId())
			.receiverCompanyId(request.getReceiverCompanyId())
			.productId(request.getProductId())
			.hubId(hubId)
			.deliveryId(deliveryId)
			.quantity(request.getQuantity())
			.requestDescription(request.getRequestDescription())
			.build();
	}

	// Entity → DTO 변환
	public CreateOrderResponse fromEntity(Order order) {
		return CreateOrderResponse.builder()
			.orderInfo(CreateOrderResponse.OrderInfo.builder()
				.orderId(order.getOrderId())
				.hubId(order.getHubId())
				.deliveryId(order.getDeliveryId())
				.quantity(order.getQuantity())
				.requestDescription(order.getRequestDescription())
				.build())
			.companyInfo(CreateOrderResponse.CompanyInfo.builder()
				.supplierCompanyId(order.getSupplierCompanyId())
				.receiverCompanyId(order.getReceiverCompanyId())
				.build())
			.productInfo(CreateOrderResponse.ProductInfo.builder()
				.productId(order.getProductId())
				.build())
			.build();
	}

}
