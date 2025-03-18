package com.teamsparta8.hub.presentation.dto.hub;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HubResponseDto {
	private UUID hubId;
	private String hubName;
	private String hubAddress;
	private BigDecimal longitude;
	private BigDecimal latitude;
}
