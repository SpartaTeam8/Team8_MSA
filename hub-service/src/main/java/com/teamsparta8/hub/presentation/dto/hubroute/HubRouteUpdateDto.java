package com.teamsparta8.hub.presentation.dto.hubroute;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;

@Getter
public class HubRouteUpdateDto {

	private UUID departureHubId;

	private UUID arrivalHubId;

	private LocalDateTime timeTaken;

	private Double distance;
}
