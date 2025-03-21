package com.teamsparta8.deliveryservice.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teamsparta8.deliveryservice.domain.model.Delivery;

public interface JpaDeliveryRepository extends JpaRepository<Delivery, UUID> {

	boolean existsByOrderId(UUID orderId);
}
