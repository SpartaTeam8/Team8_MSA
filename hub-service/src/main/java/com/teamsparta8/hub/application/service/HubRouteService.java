package com.teamsparta8.hub.application.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teamsparta8.hub.application.dto.HubRouteCreateInternalDto;
import com.teamsparta8.hub.application.dto.HubRouteResponseInternalDto;
import com.teamsparta8.hub.application.dto.HubRouteUpdateInternalDto;
import com.teamsparta8.hub.application.mapper.HubRouteMapper;
import com.teamsparta8.hub.domain.model.Hub;
import com.teamsparta8.hub.domain.model.HubRoute;
import com.teamsparta8.hub.domain.service.HubRouteDomainService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HubRouteService {

	private final HubRouteDomainService hubRouteDomainService;

	private final HubRouteMapper hubRouteMapper;

	public HubRouteResponseInternalDto createHubRoute(HubRouteCreateInternalDto request) {

		HubRoute hubRoute = hubRouteMapper.createToHubRoute(request);

		hubRouteDomainService.createHubRoute(hubRoute);

		return hubRouteMapper.hubRouteToResponse(hubRoute);
	}

	public HubRouteResponseInternalDto readHubRoute(UUID hubRouteId) {

		HubRoute hubRoute = hubRouteDomainService.readHubRoute(hubRouteId);

		return hubRouteMapper.hubRouteToResponse(hubRoute);
	}

	public Page<HubRouteResponseInternalDto> searchHubs(Pageable pageable) {

		Page<HubRoute> hubRoutes = hubRouteDomainService.searchHubRoutes(pageable);

		return hubRouteMapper.hubRoutePageToResponse(hubRoutes);
	}

	public HubRouteResponseInternalDto updateHubRoute(UUID hubRouteId, HubRouteUpdateInternalDto request) {

		HubRoute hubRoute = hubRouteDomainService.readHubRoute(hubRouteId);

		hubRoute.setDepartureHubId(request.getDepartureHubId());
		hubRoute.setArrivalHubId(request.getArrivalHubId());
		hubRoute.setTimeTaken(request.getTimeTaken());
		hubRoute.setDistance(request.getDistance());

		hubRouteDomainService.updateHubRoute(hubRoute);

		return hubRouteMapper.hubRouteToResponse(hubRoute);
	}

	public HubRouteResponseInternalDto deleteHubRoute(UUID hubRouteId) {

		HubRoute hubRoute = hubRouteDomainService.readHubRoute(hubRouteId);

		hubRoute.delete(LocalDateTime.now(), "user");

		hubRouteDomainService.deleteHubRoute(hubRoute);

		return hubRouteMapper.hubRouteToResponse(hubRoute);
	}
}
