package com.teamsparta8.deliveryservice.infrastructure.client;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.teamsparta8.deliveryservice.domain.service.HubRoutePort;
import com.teamsparta8.deliveryservice.infrastructure.client.dto.HubRouteResponseDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HubRouteAdapter implements HubRoutePort {

	private final HubRouteClient hubRouteClient;

	@Override
	public List<HubRouteResponseDto> getRoutes(UUID departureHubId, UUID arrivalHubId) {

		return hubRouteClient.getRoutes(departureHubId, arrivalHubId);
	}
}
