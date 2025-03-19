package com.teamsparta8.hub.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.teamsparta8.hub.application.dto.HubCreateInternalDto;
import com.teamsparta8.hub.application.dto.HubResponseInternalDto;
import com.teamsparta8.hub.domain.model.Hub;

@Mapper(componentModel = "spring")
public interface HubMapper {

	Hub createToHub(HubCreateInternalDto request);

	HubResponseInternalDto hubToResponse(Hub hub);

	default Page<HubResponseInternalDto> hubPageToResponse(Page<Hub> hubList){
		List<HubResponseInternalDto> hubResponseDtoList = hubList.getContent().stream()
			.map(this::hubToResponse)
			.collect(Collectors.toList());

		return new PageImpl<>(hubResponseDtoList, hubList.getPageable(), hubList.getTotalElements());
	}
}
