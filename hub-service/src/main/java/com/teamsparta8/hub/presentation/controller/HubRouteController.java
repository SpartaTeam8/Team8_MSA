package com.teamsparta8.hub.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamsparta8.hub.application.dto.HubRouteResponseInternalDto;
import com.teamsparta8.hub.application.service.HubRouteService;
import com.teamsparta8.hub.presentation.dto.CommonResponse;
import com.teamsparta8.hub.presentation.dto.Pagination;
import com.teamsparta8.hub.presentation.dto.hubroute.HubRouteCreateDto;
import com.teamsparta8.hub.presentation.dto.hubroute.HubRouteResponseDto;
import com.teamsparta8.hub.presentation.dto.hubroute.HubRouteUpdateDto;
import com.teamsparta8.hub.presentation.util.DtoMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hub-routes")
public class HubRouteController {

	private final HubRouteService hubRouteService;

	@PostMapping("")
	public CommonResponse<HubRouteResponseDto> createHubRoute(@RequestBody HubRouteCreateDto request) {

		HubRouteResponseInternalDto response = hubRouteService.createHubRoute(
			DtoMapper.convertToCreateInternalDto(request)
		);

		return CommonResponse.OK(DtoMapper.convertToResponse(response), "허브간 이동경로 생성 성공");
	}

	@GetMapping("/{hubRouteId}")
	public CommonResponse<HubRouteResponseDto> readHubRoute(@PathVariable UUID hubRouteId) {

		HubRouteResponseInternalDto response = hubRouteService.readHubRoute(hubRouteId);

		return CommonResponse.OK(DtoMapper.convertToResponse(response), "허브간 이동경로 조회 성공 : " + hubRouteId);
	}

	@GetMapping("")
	public CommonResponse<List<HubRouteResponseDto>> searchHubRoutes(Pageable pageable) {

		Page<HubRouteResponseInternalDto> hubRoutePages = hubRouteService.searchHubs(pageable);

		Pagination pagination = Pagination.builder()
			.currentElements(hubRoutePages.getNumberOfElements())
			.currentPage(hubRoutePages.getNumber())
			.totalElements(hubRoutePages.getTotalElements())
			.totalPages(hubRoutePages.getTotalPages())
			.build();

		return CommonResponse.OK(
			DtoMapper.convertToReponseList(hubRoutePages.getContent(), DtoMapper::convertToResponse),
			"허브간 이동경로 검색 성공",
			pagination
		);
	}

	@PutMapping("/{hubRouteId}")
	public CommonResponse<HubRouteResponseDto> updateHubRoute(@PathVariable UUID hubRouteId,
		@RequestBody HubRouteUpdateDto request) {

		HubRouteResponseInternalDto response = hubRouteService.updateHubRoute(
			hubRouteId, DtoMapper.convertToUpdateInternalDto(request)
		);

		return CommonResponse.OK(DtoMapper.convertToResponse(response), "허브간 이동경로 수정 성공");
	}

	@DeleteMapping("/{hubRouteId}")
	public CommonResponse<HubRouteResponseDto> deleteHubRoute(@PathVariable UUID hubRouteId) {

		HubRouteResponseInternalDto response = hubRouteService.deleteHubRoute(hubRouteId);

		return CommonResponse.OK(DtoMapper.convertToResponse(response), "허브간 이동경로 삭제 성공");
	}
}