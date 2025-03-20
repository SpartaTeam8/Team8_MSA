package com.teamsparta8.deliveryservice.presentation.util;

import java.util.List;
import java.util.stream.Collectors;

import com.teamsparta8.deliveryservice.application.dto.CreateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.application.dto.DeliveryResponseInternalDto;
import com.teamsparta8.deliveryservice.application.dto.UpdateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.domain.model.Delivery;
import com.teamsparta8.deliveryservice.presentation.dto.CreateDeliveryDto;
import com.teamsparta8.deliveryservice.presentation.dto.DeliveryResponseDto;
import com.teamsparta8.deliveryservice.presentation.dto.UpdateDeliveryDto;

public class DtoMapper {

	public static CreateDeliveryInternalDto convertToCreateInternalDto(CreateDeliveryDto request) {

		return CreateDeliveryInternalDto.builder()
			.orderId(request.getOrderId())
			.departureHubId(request.getDepartureHubId())
			.arrivalHubId(request.getArrivalHubId())
			.deliveryAddress(request.getDeliveryAddress())
			.recipient(request.getRecipient())
			.recipientSlackId(request.getRecipientSlackId())
			.build();
	}

	public static UpdateDeliveryInternalDto convertToUpdateInternalDto(UpdateDeliveryDto request){
		return UpdateDeliveryInternalDto.builder()

			.build();
	}

	public static DeliveryResponseDto convertToResponse(DeliveryResponseInternalDto response) {

		return DeliveryResponseDto.builder()
			.orderId(response.getOrderId())
			.departureHubId(response.getDepartureHubId())
			.arrivalHubId(response.getArrivalHubId())
			.deliveryAddress(response.getDeliveryAddress())
			.recipient(response.getRecipient())
			.recipientSlackId(response.getRecipientSlackId())
			.deliveryStatus(response.getDeliveryStatus())
			.deliveryRouteLogs(response.getDeliveryRouteLogs())
			.companyDeliveryManagerId(response.getCompanyDeliveryManagerId())
			.build();
	}

	public static List<DeliveryResponseDto> convertToReponseList(List<DeliveryResponseInternalDto> responses) {

		return responses.stream()
			.map(DtoMapper::convertToResponse)
			.collect(Collectors.toList());
	}
}
