package com.teamsparta8.deliveryservice.application.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateDeliveryInternalDto {

	private UUID orderId;

	private UUID departureHubId;

	private UUID arrivalHubId;

	private String deliveryAddress;

	private String recipient;

	private String recipientSlackId;
}
