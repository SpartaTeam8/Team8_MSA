package com.teamsparta8.hub.presentation.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HubDto {

	@Getter
	public static class Create {
		private String hubName;
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}

	@Getter
	public static class UpdateAddress {
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private UUID hubId;
		private String hubName;
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}
}
