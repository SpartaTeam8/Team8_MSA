package com.teamsparta8.hub.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HubCreateInternalDto {
	private String hubName;
	private String hubAddress;
	private BigDecimal longitude;
	private BigDecimal latitude;
}