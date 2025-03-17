package com.teamsparta8.hub.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teamsparta8.hub.domain.model.Hub;

public interface HubRepository {
	void save(Hub hub);

	Optional<Hub> findById(UUID hubId);

	Page<Hub> findAll(Pageable pageable);
}
