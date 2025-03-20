package com.teamsparta8.stock_service.domain.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="p_stock")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID stockId;

	@Column(nullable = false)
	private UUID productId;

	@Column(nullable = false)
	private UUID hubId;

	@Column(nullable = false)
	private int quantity;

	public void decreaseStock(int amount) {
		if (this.quantity < amount) {
			throw new IllegalStateException("재고가 부족합니다.");
		}
		this.quantity -= amount;
	}

	public void updateStock(int newQuantity) {
		if (newQuantity < 0) {
			throw new IllegalArgumentException("재고는 0 이상이어야 합니다.");
		}
		this.quantity = newQuantity;
	}
}
