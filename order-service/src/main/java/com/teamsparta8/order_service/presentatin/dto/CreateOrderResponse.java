package com.teamsparta8.order_service.presentatin.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
	private OrderInfo orderInfo;
	private CompanyInfo companyInfo;
	private ProductInfo productInfo;
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OrderInfo {
		private UUID orderId;
		private UUID hubId;
		private UUID deliveryId;
		private int quantity;
		private String requestDescription;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CompanyInfo {
		private UUID supplierCompanyId;
		private UUID receiverCompanyId;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ProductInfo {
		private UUID productId;
	}
	private int totalPrice;
}
