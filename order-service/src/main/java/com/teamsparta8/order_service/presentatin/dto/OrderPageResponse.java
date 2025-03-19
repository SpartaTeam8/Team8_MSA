package com.teamsparta8.order_service.presentatin.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderPageResponse {
	private List<CreateOrderResponse> orders;
	private Pagination pagination;
}
