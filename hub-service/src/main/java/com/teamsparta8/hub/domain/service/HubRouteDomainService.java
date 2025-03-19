package com.teamsparta8.hub.domain.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsparta8.hub.domain.model.HubRoute;
import com.teamsparta8.hub.domain.repository.HubRouteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HubRouteDomainService {

	private final HubRouteRepository hubRouteRepository;

	@Transactional
	public void createHubRoute(HubRoute hubRoute) {

		if(isDuplicated(hubRoute)) {
			throw new IllegalArgumentException("이미 존재하는 이동경로입니다.");
		}
		hubRouteRepository.save(hubRoute);
	}

	@Transactional(readOnly = true)
	public HubRoute readHubRoute(UUID hubRouteId) {

		return hubRouteRepository.findById(hubRouteId);
	}

	@Transactional(readOnly = true)
	public Page<HubRoute> searchHubRoutes(Pageable pageable) {

		return hubRouteRepository.findAll(pageable);
	}

	@Transactional
	public void updateHubRoute(HubRoute hubRoute) {
		if(isDuplicated(hubRoute)) {
			throw new IllegalArgumentException("이미 존재하는 이동경로입니다.");
		}
		hubRouteRepository.save(hubRoute);
	}

	@Transactional
	public void deleteHubRoute(HubRoute hubRoute) {

		hubRouteRepository.save(hubRoute);
	}


	public boolean isDuplicated(HubRoute hubRoute) {

		return hubRouteRepository.existsByDepartureHubIdAndArrivalHubId(
			hubRoute.getDepartureHubId(), hubRoute.getArrivalHubId()
		);
	}

}
