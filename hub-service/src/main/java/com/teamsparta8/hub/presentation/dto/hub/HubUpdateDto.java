package com.teamsparta8.hub.presentation.dto.hub;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class HubUpdateDto {
		private String hubName;
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
}
