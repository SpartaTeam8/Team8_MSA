package com.teamsparta8.hub.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HubRouteResponseInternalDto {

		private UUID hubRouteId;

		private UUID departureHubId;

		private UUID arrivalHubId;

		private LocalDateTime timeTaken;

		private Double distance;
}
