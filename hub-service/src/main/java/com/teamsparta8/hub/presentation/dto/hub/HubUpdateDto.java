package com.teamsparta8.hub.presentation.dto.hub;

import java.math.BigDecimal;

import lombok.Getter;

public class HubUpdateDto {

	@Getter
	public static class Address {
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}

	@Getter
	public static class Name{
		private String hubName;
	}

	@Getter
	public static class All{
		private String hubName;
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}
}
