package com.teamsparta8.deliveryservice.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Builder
public class DeliveryRouteLogResponseInternalDto {

    private UUID deliveryId;

    private UUID deliveryRouteLogId;

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
