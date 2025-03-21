package com.teamsparta8.deliveryservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {
	PENDING("배송 대기중"),
	HUB_WAITING("허브 대기중"),
	HUB_MOVING("허브 이동중"),
	ARRIVED_AT_DESTINATION_HUB("목적지 허브 도착"),
	DELIVERING("배송중"),
	MOVING_TO_COMPANY("업체이동중"),
	DELIVERY_COMPLETED("배송완료");

	private final String description;
}
