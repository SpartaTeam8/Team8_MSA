package com.teamsparta8.deliveryservice.application.dto;

import java.util.List;
import java.util.UUID;

import com.teamsparta8.deliveryservice.domain.model.DeliveryRouteLog;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryResponseInternalDto {

	private UUID orderId;

	private UUID departureHubId;

	private UUID arrivalHubId;

	private String deliveryAddress;

	private String recipient;

	private String recipientSlackId;

	private String deliveryStatus;

	private UUID companyDeliveryManagerId;

	private List<DeliveryRouteLog> deliveryRouteLogs;
}
