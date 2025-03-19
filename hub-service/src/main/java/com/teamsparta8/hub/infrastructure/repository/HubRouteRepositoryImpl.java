package com.teamsparta8.hub.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.teamsparta8.hub.domain.model.HubRoute;
import com.teamsparta8.hub.domain.repository.HubRouteRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HubRouteRepositoryImpl implements HubRouteRepository {

	private final JpaHubRouteRepository jpaHubRouteRepository;

	@Override
	public void save(HubRoute hubRoute) {

		jpaHubRouteRepository.save(hubRoute);
	}

	@Override
	public HubRoute findById(UUID hubRouteId) {

		return jpaHubRouteRepository.findById(hubRouteId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 허브간 이동경로 입니다."));
	}

	@Override
	public Page<HubRoute> findAll(Pageable pageable) {

		return jpaHubRouteRepository.findAll(pageable);
	}

	@Override
	public boolean existsByDepartureHubIdAndArrivalHubId(UUID departureHubId, UUID arrivalHubId) {

		return jpaHubRouteRepository.existsByDepartureHubIdAndArrivalHubId(departureHubId, arrivalHubId);
	}
}
