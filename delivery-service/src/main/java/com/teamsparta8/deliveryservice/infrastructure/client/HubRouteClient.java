package com.teamsparta8.deliveryservice.infrastructure.client;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamsparta8.deliveryservice.infrastructure.client.dto.HubRouteResponseDto;

@FeignClient(name = "hub-service", url = "http://api/api/hubs")
public interface HubRouteClient {

	@GetMapping("/routes")
	List<HubRouteResponseDto> getRoutes(@RequestParam UUID departureHubId, @RequestParam UUID arrivalHubId);

}
