package com.teamsparta8.deliveryservice.domain.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "p_delivery_route_log")
public class DeliveryRouteLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID deliveryRouteLogId;

	@Column(nullable = false)
	private Integer sequence;

	@Column(nullable = false)
	private UUID departureHubId;

	@Column(nullable = false)
	private UUID arrivalHubId;

	// 예상 거리
	@Column(nullable = false)
	private Double expectedDistance;

	// 예상 소요시간
	@Column(nullable = false)
	private LocalTime expectedDuration;

	// 실제 이동 거리
	@Column(nullable = false)
	private Double actualDistance;

	// 실제 소요 시간
	@Column(nullable = false)
	private LocalTime timeTaken;

	@Column(nullable = false)
	private DeliveryStatus deliveryStatus;

	@Column(nullable = false)
	private UUID hubDeliveryManagerId;

	@ManyToOne
	@JoinColumn(name = "delivery_id", nullable = false)
	@JsonBackReference
	private Delivery delivery;
}
