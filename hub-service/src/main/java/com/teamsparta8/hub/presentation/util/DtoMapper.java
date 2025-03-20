package com.teamsparta8.hub.presentation.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.teamsparta8.hub.application.dto.HubCreateInternalDto;
import com.teamsparta8.hub.application.dto.HubResponseInternalDto;
import com.teamsparta8.hub.application.dto.HubRouteCreateInternalDto;
import com.teamsparta8.hub.application.dto.HubRouteResponseInternalDto;
import com.teamsparta8.hub.application.dto.HubRouteUpdateInternalDto;
import com.teamsparta8.hub.application.dto.HubUpdateInternalDto;
import com.teamsparta8.hub.presentation.dto.hub.HubCreateDto;
import com.teamsparta8.hub.presentation.dto.hub.HubResponseDto;
import com.teamsparta8.hub.presentation.dto.hub.HubUpdateDto;
import com.teamsparta8.hub.presentation.dto.hubroute.HubRouteCreateDto;
import com.teamsparta8.hub.presentation.dto.hubroute.HubRouteResponseDto;
import com.teamsparta8.hub.presentation.dto.hubroute.HubRouteUpdateDto;

public class DtoMapper {

	public static HubCreateInternalDto convertToCreateInternalDto(HubCreateDto create) {

		return HubCreateInternalDto.builder()
			.hubName(create.getHubName())
			.hubAddress(create.getHubAddress())
			.longitude(create.getLongitude())
			.latitude(create.getLatitude())
			.build();
	}

	public static HubRouteCreateInternalDto convertToCreateInternalDto(HubRouteCreateDto route) {

		return HubRouteCreateInternalDto.builder()
			.departureHubId(route.getDepartureHubId())
			.arrivalHubId(route.getArrivalHubId())
			.timeTaken(route.getTimeTaken())
			.distance(route.getDistance())
			.build();
	}

	public static HubUpdateInternalDto convertToUpdateInternalDto(HubUpdateDto update) {
		return HubUpdateInternalDto.builder()
			.hubName(update.getHubName())
			.hubAddress(update.getHubAddress())
			.longitude(update.getLongitude())
			.latitude(update.getLatitude())
			.build();
	}

	public static HubRouteUpdateInternalDto convertToUpdateInternalDto(HubRouteUpdateDto update) {
		return HubRouteUpdateInternalDto.builder()
			.departureHubId(update.getDepartureHubId())
			.arrivalHubId(update.getArrivalHubId())
			.timeTaken(update.getTimeTaken())
			.distance(update.getDistance())
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

	public static HubRouteResponseDto convertToResponse(HubRouteResponseInternalDto response) {

		return HubRouteResponseDto.builder()
			.hubRouteId(response.getHubRouteId())
			.departureHubId(response.getDepartureHubId())
			.arrivalHubId(response.getArrivalHubId())
			.timeTaken(response.getTimeTaken())
			.distance(response.getDistance())
			.build();
	}

	public static <T,R> List<R> convertToReponseList(List<T> responses, Function<T,R> mapper) {

		return responses.stream()
			.map(mapper)
			.collect(Collectors.toList());
	}
}
