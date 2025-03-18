package com.teamsparta8.hub.domain.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsparta8.hub.domain.model.Hub;
import com.teamsparta8.hub.domain.repository.HubRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HubDomainService {

	private final HubRepository hubRepository;

	@Transactional
	public void createHub(Hub hub) {

		if(isDuplicated(hub)) {
			throw new IllegalArgumentException("중복된 허브 입니다.");
		}

		hubRepository.save(hub);
	}

	public Hub readHub(UUID hubId) {
		return hubRepository.findById(hubId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 허브입니다."));
	}

	public Page<Hub> searchHubs(Pageable pageable) {

		return hubRepository.findAll(pageable);
	}

	@Transactional
	public void updateHub(Hub hub) {

		if(isDuplicated(hub)) {
			throw new IllegalArgumentException("중복된 허브 입니다.");
		}

		hubRepository.save(hub);
	}

	@Transactional
	public void deleteHub(Hub hub) {

		hubRepository.save(hub);
	}

	public boolean isDuplicated(Hub hub) {

		return hubRepository.existsByHubNameAndHubAddress(hub.getHubName(), hub.getHubAddress());
	}
}
