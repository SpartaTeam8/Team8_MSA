package com.teamsparta8.hub.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HubInternalDto {

	@Getter
	@Builder
	public static class All {
		private String hubName;
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}

	@Getter
	@Builder
	public static class Address {
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}

	@Getter
	@Builder
	public static class Name {
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}
}
