package com.teamsparta8.deliveryservice.infrastructure.client.dto;

import java.time.LocalTime;
import java.util.UUID;

import lombok.Getter;

@Getter
public class HubRouteResponseDto {

	private Integer sequence;

	private UUID departureHubId;

	private UUID arrivalHubId;

	private LocalTime expectedDuration;

	private Double expectedDistance;
}
