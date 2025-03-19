package com.teamsparta8.hub.presentation.dto.hubroute;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HubRouteResponseDto {

	private UUID hubRouteId;

	private UUID departureHubId;

	private UUID arrivalHubId;

	private LocalDateTime timeTaken;

	private Double distance;
}
