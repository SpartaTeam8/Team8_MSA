package com.teamsparta8.hub.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamsparta8.hub.domain.model.Hub;

public interface JpaHubRepository extends JpaRepository<Hub, UUID> {

	boolean existsByHubNameAndHubAddress(String hubName, String hubAddress);
}
