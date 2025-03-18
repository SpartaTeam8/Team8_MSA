package com.teamsparta8.hub.application.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HubUpdateInternalDto {
	private String hubName;
	private String hubAddress;
	private BigDecimal longitude;
	private BigDecimal latitude;
}
