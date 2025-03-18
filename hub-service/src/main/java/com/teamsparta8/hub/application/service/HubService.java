package com.teamsparta8.hub.application.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teamsparta8.hub.application.dto.HubCreateInternalDto;
import com.teamsparta8.hub.application.dto.HubResponseInternalDto;
import com.teamsparta8.hub.application.dto.HubUpdateInternalDto;
import com.teamsparta8.hub.domain.model.Hub;
import com.teamsparta8.hub.domain.service.HubDomainService;
import com.teamsparta8.hub.presentation.dto.hub.HubUpdateDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HubService {

	private final HubDomainService hubDomainService;

	private final HubMapper hubMapper;

	public HubResponseInternalDto createHub(HubCreateInternalDto request) {

		Hub hub = hubMapper.createToHub(request);

		hubDomainService.createHub(hub);

		return hubMapper.hubToResponse(hub);
	}

	public HubResponseInternalDto readHub(UUID hubId) {

		Hub hub = hubDomainService.readHub(hubId);

		return hubMapper.hubToResponse(hub);
	}

	public Page<HubResponseInternalDto> searchHubs(Pageable pageable) {

		Page<Hub> hubs = hubDomainService.searchHubs(pageable);

		return hubMapper.hubListToResponse(hubs);
	}

	public HubResponseInternalDto updateHub(UUID hubId, HubUpdateInternalDto request) {

		Hub hub = hubDomainService.readHub(hubId);

		hub.setHubName(request.getHubName());

		hub.setHubAddress(request.getHubAddress());

		hub.setLongitude(request.getLongitude());

		hub.setLatitude(request.getLatitude());

		hubDomainService.updateHub(hub);

		return hubMapper.hubToResponse(hub);
	}

	// 주소를 이전하는 비즈니스 로직으로 위도와 경도 같이 수정
	public HubResponseInternalDto updateHubPartially(UUID hubId, HubUpdateInternalDto request) {

		Hub hub = hubDomainService.readHub(hubId);

		if(request.getHubName() != null) {
			hub.setHubName(request.getHubName());
		}

		if(request.getHubAddress() != null) {
			hub.setHubAddress(request.getHubAddress());
		}

		hubDomainService.updateHub(hub);

		return hubMapper.hubToResponse(hub);
	}

	@Transactional
	public HubResponseInternalDto deleteHub(UUID hubId) {
		Hub hub = hubDomainService.readHub(hubId);

		hub.delete(LocalDateTime.now(), "user");

		hubDomainService.updateHub(hub);



		// deletedBy는 아직 미적용


		return hubMapper.hubToResponse(hub);
	}
}

