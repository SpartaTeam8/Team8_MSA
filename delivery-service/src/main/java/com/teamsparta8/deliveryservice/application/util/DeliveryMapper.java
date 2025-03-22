package com.teamsparta8.deliveryservice.application.util;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.teamsparta8.deliveryservice.application.dto.CreateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.application.dto.DeliveryResponseInternalDto;
import com.teamsparta8.deliveryservice.domain.model.Delivery;
import com.teamsparta8.deliveryservice.domain.model.DeliveryRouteLog;
import com.teamsparta8.deliveryservice.domain.model.DeliveryStatus;
import com.teamsparta8.deliveryservice.infrastructure.client.dto.HubRouteResponseDto;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

	default Delivery createToDelivery(CreateDeliveryInternalDto request) {

		return Delivery.builder()
			.orderId(request.getOrderId())
			.departureHubId(request.getDepartureHubId())
			.arrivalHubId(request.getArrivalHubId())
			.deliveryAddress(request.getDeliveryAddress())
			.deliveryStatus(DeliveryStatus.PENDING)
			.recipient(request.getRecipient())
			.recipientSlackId(request.getRecipientSlackId())
			.build();
	}

	DeliveryResponseInternalDto deliveryToResponse(Delivery delivery);

	default Page<DeliveryResponseInternalDto> deliveryPageToResponse(Page<Delivery> deliveryPage) {

		List<DeliveryResponseInternalDto> deliveryList = deliveryPage.getContent().stream()
			.map(this::deliveryToResponse)
			.collect(Collectors.toList());

		return new PageImpl<>(deliveryList, deliveryPage.getPageable(), deliveryPage.getTotalElements());
	}

	default DeliveryRouteLog toDeliveryRouteLog(HubRouteResponseDto dto) {

		return DeliveryRouteLog.builder()
			.sequence(dto.getSequence())
			.departureHubId(dto.getDepartureHubId())
			.arrivalHubId(dto.getArrivalHubId())
			.deliveryStatus(DeliveryStatus.PENDING)
			.expectedDistance(dto.getExpectedDistance())
			.expectedDuration(dto.getExpectedDuration())
			.actualDistance(0.0)
			.timeTaken(LocalTime.of(0, 0, 0))
			.build();
	}

	default List<DeliveryRouteLog> toDeliveryRouteLogList(List<HubRouteResponseDto> dtoList) {

		return dtoList.stream()
			.map(this::toDeliveryRouteLog)
			.collect(Collectors.toList());
	}
}
