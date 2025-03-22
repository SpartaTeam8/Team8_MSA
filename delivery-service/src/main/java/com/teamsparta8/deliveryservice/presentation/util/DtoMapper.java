package com.teamsparta8.deliveryservice.presentation.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.teamsparta8.deliveryservice.application.dto.CreateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.application.dto.DeliveryResponseInternalDto;
import com.teamsparta8.deliveryservice.application.dto.DeliveryRouteLogResponseInternalDto;
import com.teamsparta8.deliveryservice.application.dto.UpdateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.domain.model.Delivery;
import com.teamsparta8.deliveryservice.presentation.dto.CreateDeliveryDto;
import com.teamsparta8.deliveryservice.presentation.dto.DeliveryResponseDto;
import com.teamsparta8.deliveryservice.presentation.dto.DeliveryRouteLogResponseDto;
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

    public static UpdateDeliveryInternalDto convertToUpdateInternalDto(UpdateDeliveryDto request) {
        return UpdateDeliveryInternalDto.builder()
                .companyDeliveryManagerId(request.getCompanyDeliveryManagerId())
                .deliveryStatus(request.getDeliveryStatus())
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

    public static DeliveryRouteLogResponseDto convertToResponse(DeliveryRouteLogResponseInternalDto response) {

        return DeliveryRouteLogResponseDto.builder()
                .deliveryRouteLogId(response.getDeliveryRouteLogId())
                .deliveryId(response.getDeliveryId())
                .sequence(response.getSequence())
                .departureHubId(response.getDepartureHubId())
                .arrivalHubId(response.getArrivalHubId())
                .expectedDistance(response.getExpectedDistance())
                .expectedDuration(response.getExpectedDuration())
                .actualDistance(response.getActualDistance())
                .timeTaken(response.getTimeTaken())
                .hubDeliveryManagerId(response.getHubDeliveryManagerId())
                .deliveryStatus(response.getDeliveryStatus())
                .build();
    }

    public static <T, R> List<R> convertToReponseList(List<T> responses, Function<T, R> mapper) {

        return responses.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
