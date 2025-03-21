package com.teamsparta8.hub.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_hub_route")
public class HubRoute extends BaseEntity {

	@Id
	@Column(updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID hubRouteId;

	@Column(nullable = false)
	private UUID departureHubId;

	@Column(nullable = false)
	private UUID arrivalHubId;

	// 00:00:00 형식으로 저장 예상
	@Column(nullable = false)
	private LocalDateTime timeTaken;

	// 거리 단위 : km , 소수점 단위로 m 표현
	@Column(nullable = false)
	private Double distance;
}
