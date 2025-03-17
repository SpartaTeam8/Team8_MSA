package com.teamsparta8.hub.presentation.util;

import java.util.List;
import java.util.stream.Collectors;

import com.teamsparta8.hub.application.dto.HubInternalDto;
import com.teamsparta8.hub.presentation.dto.HubDto;

public class DtoMapper {

	public static HubInternalDto.Create convertToCreateInternalDto(HubDto.Create create) {

		return HubInternalDto.Create.builder()
			.hubName(create.getHubName())
			.hubAddress(create.getHubAddress())
			.longitude(create.getLongitude())
			.latitude(create.getLatitude())
			.build();
	}

	public static HubDto.Response convertToResponse(HubInternalDto.Response response) {

		return HubDto.Response.builder()
			.hubId(response.getHubId())
			.hubName(response.getHubName())
			.hubAddress(response.getHubAddress())
			.longitude(response.getLongitude())
			.latitude(response.getLatitude())
			.build();
	}

	public static List<HubDto.Response> convertToReponseList(List<HubInternalDto.Response> responses) {

		return responses.stream()
			.map(DtoMapper::convertToResponse)
			.collect(Collectors.toList());
	}
}
