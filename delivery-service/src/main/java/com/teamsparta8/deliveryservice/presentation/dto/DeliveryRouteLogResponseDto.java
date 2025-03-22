package com.teamsparta8.deliveryservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Builder
public class DeliveryRouteLogResponseDto {

    private UUID deliveryRouteLogId;

    private UUID deliveryId;

    private Integer sequence;

    private UUID departureHubId;

    private UUID arrivalHubId;

    private Double expectedDistance;

    private LocalTime expectedDuration;

    private Double actualDistance;

    private LocalTime timeTaken;

    private String deliveryStatus;

    private UUID hubDeliveryManagerId;
}
