package com.teamsparta8.productservice.application.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateInternalDto { // 잠시만유

	private UUID companyId;
	private UUID hubId;
	private String productName;
	private int price;
	private boolean isDeleted;
}
