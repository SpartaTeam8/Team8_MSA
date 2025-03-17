package com.teamsparta8.hub.application.service;

import java.util.List;

import org.mapstruct.Mapper;

import com.teamsparta8.hub.application.dto.HubInternalDto;
import com.teamsparta8.hub.domain.model.Hub;

@Mapper(componentModel = "spring")
public interface HubMapper {

	Hub createToHub(HubInternalDto.Create request);

	HubInternalDto.Response hubToResponse(Hub hub);

	List<HubInternalDto.Response> hubListToResponse(List<Hub> hubList);
}
