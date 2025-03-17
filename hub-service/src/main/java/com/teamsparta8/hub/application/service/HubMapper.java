package com.teamsparta8.hub.application.service;

import java.util.List;

import org.mapstruct.Mapper;

import com.teamsparta8.hub.application.dto.HubCreateInternalDto;
import com.teamsparta8.hub.application.dto.HubInternalDto;
import com.teamsparta8.hub.application.dto.HubResponseInternalDto;
import com.teamsparta8.hub.domain.model.Hub;

@Mapper(componentModel = "spring")
public interface HubMapper {

	Hub createToHub(HubCreateInternalDto request);

	HubResponseInternalDto hubToResponse(Hub hub);

	List<HubResponseInternalDto> hubListToResponse(List<Hub> hubList);
}
