package com.teamsparta8.productservice.domain.entity;

import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;

import com.teamsparta8.productservice.common.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="p_product")
@Entity
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID productId;

	@NotNull
	private UUID companyId;

	@NotNull
	private UUID hubId;

	@NotNull
	private String productName;

	@NotNull
	@Min(0)
	private int price;

	@NotNull
	@ColumnDefault("false")
	private boolean isDeleted;
}
