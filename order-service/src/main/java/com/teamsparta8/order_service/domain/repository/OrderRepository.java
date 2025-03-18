package com.teamsparta8.order_service.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teamsparta8.order_service.domain.model.Order;


public interface OrderRepository {
	Optional<Order> findById(UUID orderId);
	Page<Order> findAll(Pageable pageable);
	Order save(Order order);
	void deleteById(UUID orderId);
}
