package com.teamsparta8.order_service.presentatin.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

	@NotNull
	private UUID productId;
	@NotNull
	private UUID supplierCompanyId;

	@NotNull
	private UUID receiverCompanyId;

	@NotNull
	private int quantity;

	private String requestDescription;
}
