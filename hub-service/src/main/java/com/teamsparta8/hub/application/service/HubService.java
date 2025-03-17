package com.teamsparta8.hub.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teamsparta8.hub.application.dto.HubInternalDto;
import com.teamsparta8.hub.domain.model.Hub;
import com.teamsparta8.hub.domain.service.HubDomainService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HubService {

	private final HubDomainService hubDomainService;

	private final HubMapper hubMapper;

	public HubInternalDto.Response createHub(HubInternalDto.Create request) {

		Hub hub = hubMapper.createToHub(request);

		return hubMapper.hubToResponse(hub);
	}

	public HubInternalDto.Response readHub(UUID hubId) {
		Hub hub = hubDomainService.readHub(hubId);

		return hubMapper.hubToResponse(hub);
	}

	public List<HubInternalDto.Response> searchHubs(Pageable pageable) {
		Page<Hub> hubs = hubDomainService.searchHubs(pageable);

		return hubMapper.hubListToResponse(hubs.getContent());
	}
}

