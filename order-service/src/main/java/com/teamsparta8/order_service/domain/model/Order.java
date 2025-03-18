package com.teamsparta8.order_service.domain.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "p_order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) //직접호출 방지(Builder만 사용)
public class Order extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID orderId;

	@Column(nullable = false)
	private UUID supplierCompanyId;

	@Column(nullable = false)
	private UUID receiverCompanyId;

	@Column(nullable = false)
	private UUID productId;

	@Column
	private UUID hubId;

	@Column
	private UUID deliveryId;

	@Column(nullable = false)
	private int quantity;

	@Column(length = 255)
	private String requestDescription;

	public void updateHubId(UUID hubId) {
		this.hubId = hubId;
	}

	public void updateDeliveryId(UUID deliveryId) {
		this.deliveryId = deliveryId;
	}

}
