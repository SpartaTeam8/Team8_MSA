package com.teamsparta8.deliveryservice.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestParam;

import com.teamsparta8.deliveryservice.infrastructure.client.dto.HubRouteResponseDto;

public interface HubRoutePort {

	List<HubRouteResponseDto> getRoutes(@RequestParam UUID departureHubId, @RequestParam UUID arrivalHubId);
}
