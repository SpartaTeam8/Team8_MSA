package com.teamsparta8.deliveryservice.application.util;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.teamsparta8.deliveryservice.application.dto.CreateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.application.dto.DeliveryResponseInternalDto;
import com.teamsparta8.deliveryservice.domain.model.Delivery;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
	Delivery createToDelivery(CreateDeliveryInternalDto request);

	DeliveryResponseInternalDto deliveryToResponse(Delivery delivery);

	default Page<DeliveryResponseInternalDto> deliveryPageToResponse(Page<Delivery> deliveryPage){
		List<DeliveryResponseInternalDto> deliveryList = deliveryPage.getContent().stream()
			.map(this::deliveryToResponse)
			.collect(Collectors.toList());

		return new PageImpl<>(deliveryList, deliveryPage.getPageable(), deliveryPage.getTotalElements());
	}
}
