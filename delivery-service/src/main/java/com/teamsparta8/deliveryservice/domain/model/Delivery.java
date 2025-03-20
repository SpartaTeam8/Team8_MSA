package com.teamsparta8.deliveryservice.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "p_delivery")
public class Delivery extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID deliveryId;

	@Column(nullable = false)
	private UUID orderId;

	@Column(nullable = false)
	private UUID departureHubId;

	@Column(nullable = false)
	private UUID arrivalHubId;

	@Column(nullable = false)
	private String deliveryAddress;

	@Column(nullable = false)
	private String recipient;

	@Column(nullable = false)
	private String recipientSlackId;

	@Column(nullable = false)
	private DeliveryStatus status;

	@Column(nullable = false)
	private UUID companyDeliveryManagerId;

	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<DeliveryRouteLog> deliveryRouteLogs = new ArrayList<>();
}
