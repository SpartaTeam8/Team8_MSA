package com.teamsparta8.hub.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamsparta8.hub.application.dto.HubInternalDto;
import com.teamsparta8.hub.application.service.HubService;
import com.teamsparta8.hub.presentation.dto.CommonResponse;
import com.teamsparta8.hub.presentation.dto.HubDto;
import com.teamsparta8.hub.presentation.dto.Pagination;
import com.teamsparta8.hub.presentation.util.DtoMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hubs")
public class HubController {

	private final HubService hubService;

	@PostMapping
	public CommonResponse<HubDto.Response> create(@RequestBody HubDto.Create request) {

		HubInternalDto.Response response = hubService.createHub(DtoMapper.convertToCreateInternalDto(request));

		HubDto.Response hubDtoResponse = DtoMapper.convertToResponse(response);

		return CommonResponse.OK(hubDtoResponse, "허브 생성 성공");
	}

	@GetMapping("")
	public CommonResponse<List<HubDto.Response>> searchHubs(Pageable pageable) {
		List<HubInternalDto.Response> responseList = hubService.searchHubs(pageable);

		return CommonResponse.OK(DtoMapper.convertToReponseList(responseList), "허브 리스트 조회 성공", new Pagination());
	}

	@GetMapping("/{hubId}")
	public CommonResponse<HubDto.Response> readHub(@PathVariable("hubId") String hubId) {

		HubInternalDto.Response response = hubService.readHub(UUID.fromString(hubId));

		return CommonResponse.OK(DtoMapper.convertToResponse(response), "허브 조회 성공");
	}
}
