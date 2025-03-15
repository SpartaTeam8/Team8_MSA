package com.teamsparta8.hub.service;

import org.springframework.stereotype.Service;

import com.teamsparta8.hub.repository.HubRouteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HubService {

	private final HubRouteRepository hubRouteRepository;
}

