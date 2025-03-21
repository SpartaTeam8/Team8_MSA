package com.teamsparta8.hub.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.teamsparta8.hub.application.dto.HubResponseInternalDto;
import com.teamsparta8.hub.application.dto.HubRouteCreateInternalDto;
import com.teamsparta8.hub.application.dto.HubRouteResponseInternalDto;
import com.teamsparta8.hub.domain.model.Hub;
import com.teamsparta8.hub.domain.model.HubRoute;

@Mapper(componentModel = "spring")
public interface HubRouteMapper {
	HubRoute createToHubRoute(HubRouteCreateInternalDto request);

	HubRouteResponseInternalDto hubRouteToResponse(HubRoute hubRoute);

	default Page<HubRouteResponseInternalDto> hubRoutePageToResponse(Page<HubRoute> hubRouteList){
		List<HubRouteResponseInternalDto> hubResponseDtoList = hubRouteList.getContent().stream()
			.map(this::hubRouteToResponse)
			.collect(Collectors.toList());

		return new PageImpl<>(hubResponseDtoList, hubRouteList.getPageable(), hubRouteList.getTotalElements());
	}
}
