package com.teamsparta8.deliveryservice.presentation.dto;

import java.util.UUID;

import com.teamsparta8.deliveryservice.domain.model.DeliveryStatus;

import lombok.Getter;

@Getter
public class UpdateDeliveryDto {

	private UUID companyDeliveryManagerId;

	private String deliveryStatus;
}
