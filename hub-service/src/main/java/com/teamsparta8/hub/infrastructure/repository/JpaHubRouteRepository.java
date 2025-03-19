package com.teamsparta8.hub.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamsparta8.hub.domain.model.HubRoute;

public interface JpaHubRouteRepository extends JpaRepository<HubRoute, UUID> {

	boolean existsByDepartureHubIdAndArrivalHubId(UUID departureHubId, UUID arrivalHubId);
}
