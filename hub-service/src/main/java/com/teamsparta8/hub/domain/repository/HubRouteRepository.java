package com.teamsparta8.hub.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teamsparta8.hub.domain.model.HubRoute;

public interface HubRouteRepository {

	void save(HubRoute hubRoute);

	HubRoute findById(UUID hubRouteId);

	Page<HubRoute> findAll(Pageable pageable);

	boolean existsByDepartureHubIdAndArrivalHubId(UUID departureHubId, UUID arrivalHubId);
}
