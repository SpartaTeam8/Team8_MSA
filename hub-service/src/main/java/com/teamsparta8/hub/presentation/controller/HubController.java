package com.teamsparta8.hub.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamsparta8.hub.application.dto.HubResponseInternalDto;
import com.teamsparta8.hub.application.service.HubService;
import com.teamsparta8.hub.presentation.dto.CommonResponse;
import com.teamsparta8.hub.presentation.dto.hub.HubCreateDto;
import com.teamsparta8.hub.presentation.dto.hub.HubResponseDto;
import com.teamsparta8.hub.presentation.dto.Pagination;
import com.teamsparta8.hub.presentation.dto.hub.HubUpdateDto;
import com.teamsparta8.hub.presentation.util.DtoMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hubs")
public class HubController {

	private final HubService hubService;

	@PostMapping
	public CommonResponse<HubResponseDto> create(@RequestBody HubCreateDto request) {

		HubResponseInternalDto response = hubService.createHub(DtoMapper.convertToCreateInternalDto(request));

		HubResponseDto hubDtoResponse = DtoMapper.convertToResponse(response);

		return CommonResponse.OK(hubDtoResponse, "허브 생성 성공");
	}

	@GetMapping("")
	public CommonResponse<List<HubResponseDto>> searchHubs(Pageable pageable) {

		Page<HubResponseInternalDto> hubPages = hubService.searchHubs(pageable);

		Pagination pagination = Pagination.builder()
			.currentElements(hubPages.getNumberOfElements())
			.currentPage(hubPages.getNumber())
			.totalElements(hubPages.getTotalElements())
			.totalPages(hubPages.getTotalPages())
			.build();

		return CommonResponse.OK(
			DtoMapper.convertToReponseList(hubPages.getContent(), DtoMapper::convertToResponse),
			"허브 리스트 조회 성공",
			pagination
		);
	}

	@GetMapping("/{hubId}")
	public CommonResponse<HubResponseDto> readHub(@PathVariable("hubId") UUID hubId) {

		HubResponseInternalDto response = hubService.readHub(hubId);

		return CommonResponse.OK(DtoMapper.convertToResponse(response), "허브 조회 성공");
	}

	@PatchMapping("/{hubId}")
	public CommonResponse<HubResponseDto> updateHubPartially(@PathVariable("hubId") UUID hubId, @RequestBody HubUpdateDto request) {

		DtoMapper.convertToUpdateInternalDto(request);

		HubResponseInternalDto response = hubService.updateHubPartially(hubId, DtoMapper.convertToUpdateInternalDto(request));

		return CommonResponse.OK(DtoMapper.convertToResponse(response), "허브 주소 수정 성공");
	}

	@PutMapping("/{hubId}")
	public CommonResponse<HubResponseDto> updateHub(@PathVariable("hubId") UUID hudId, @RequestBody HubUpdateDto request) {

		HubResponseInternalDto response = hubService.updateHub(hudId, DtoMapper.convertToUpdateInternalDto(request));

		return CommonResponse.OK(DtoMapper.convertToResponse(response), "허브 수정 성공");
	}
}

