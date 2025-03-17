package com.teamsparta8.hub.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.teamsparta8.hub.domain.model.Hub;
import com.teamsparta8.hub.domain.repository.HubRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HubRepositoryImpl implements HubRepository {

	private final JpaHubRepository jpaHubRepository;

	@Override
	public void save(Hub hub) {
		jpaHubRepository.save(hub);
	}

	@Override
	public Optional<Hub> findById(UUID hubId) {
		return jpaHubRepository.findById(hubId);
	}

	@Override
	public Page<Hub> findAll(Pageable pageable) {
		return jpaHubRepository.findAll(pageable);
	}
}