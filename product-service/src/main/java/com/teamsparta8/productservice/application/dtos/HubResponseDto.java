package com.teamsparta8.productservice.application.dtos;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HubResponseDto {

	private UUID hubId;
	private UUID userId;
	private String hubName;
}
