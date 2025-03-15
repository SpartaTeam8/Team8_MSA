package com.teamsparta8.hub.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "p_hub")
public class Hub {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID hubId;

	@Column(nullable = false)
	private String hubName;

	@Column(nullable = false)
	private String hubAddress;

	@Column(nullable = false)
	private BigDecimal longitude; // 경도

	@Column(nullable = false)
	private BigDecimal latitude; // 위도
}
