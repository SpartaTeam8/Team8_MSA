package com.teamsparta8.deliveryservice.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UpdateDeliveryInternalDto {

    private UUID companyDeliveryManagerId;

    private String deliveryStatus;
}
