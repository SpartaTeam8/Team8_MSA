package com.teamsparta8.hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamsparta8.hub.entity.Hub;

public interface HubRepository extends JpaRepository<Hub, Long> {
}
