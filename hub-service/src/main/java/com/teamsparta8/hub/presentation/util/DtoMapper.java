package com.teamsparta8.hub.presentation.util;

import java.util.List;
import java.util.stream.Collectors;

import com.teamsparta8.hub.application.dto.HubCreateInternalDto;
import com.teamsparta8.hub.application.dto.HubInternalDto;
import com.teamsparta8.hub.application.dto.HubResponseInternalDto;
import com.teamsparta8.hub.presentation.dto.hub.HubCreateDto;
import com.teamsparta8.hub.presentation.dto.hub.HubResponseDto;

public class DtoMapper {

	public static HubCreateInternalDto convertToCreateInternalDto(HubCreateDto create) {

		return HubCreateInternalDto.builder()
			.hubName(create.getHubName())
			.hubAddress(create.getHubAddress())
			.longitude(create.getLongitude())
			.latitude(create.getLatitude())
			.build();
	}

	public static HubResponseDto convertToResponse(HubResponseInternalDto response) {

		return HubResponseDto.builder()
			.hubId(response.getHubId())
			.hubName(response.getHubName())
			.hubAddress(response.getHubAddress())
			.longitude(response.getLongitude())
			.latitude(response.getLatitude())
			.build();
	}

	public static List<HubResponseDto> convertToReponseList(List<HubResponseInternalDto> responses) {

		return responses.stream()
			.map(DtoMapper::convertToResponse)
			.collect(Collectors.toList());
	}
}
