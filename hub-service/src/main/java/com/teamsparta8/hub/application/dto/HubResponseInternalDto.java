package com.teamsparta8.hub.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class HubResponseInternalDto {
	private UUID hubId;
	private String hubName;
	private String hubAddress;
	private BigDecimal longitude;
	private BigDecimal latitude;
}
