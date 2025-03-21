package com.teamsparta8.deliveryservice.presentation.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class CreateDeliveryDto {

	private UUID orderId;

	private UUID departureHubId;

	private UUID arrivalHubId;

	private String deliveryAddress;

	private String recipient;

	private String recipientSlackId;
}
