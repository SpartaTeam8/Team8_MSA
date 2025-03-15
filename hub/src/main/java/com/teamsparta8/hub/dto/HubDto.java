package com.teamsparta8.hub.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


public class HubDto {

	@Getter
	@Setter
	public static class Create{
		private String hubName;
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}

	@Getter
	@Setter
	public static class UpdateAddress{
		private String hubAddress;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}
}
