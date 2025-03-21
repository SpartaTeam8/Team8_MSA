package com.teamsparta8.deliveryservice.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.teamsparta8.deliveryservice.domain.model.Delivery;

@Repository
public interface DeliveryRepository {
	void save(Delivery delivery);

	Delivery findById(UUID deliveryId);

	boolean existsByOrderId(UUID orderId);
}
