package com.teamsparta8.order_service.presentatin.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {
	@Min(value = 1, message = "수량은 1 이상이어야 합니다.")
	private int quantity;

	private String requestDescription;
}
