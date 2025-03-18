package com.teamsparta8.order_service.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.domain.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
	private final JpaOrderRepository jpaOrderRepository; // JPA Repository 주입

	@Override
	public Optional<Order> findById(UUID orderId) {
		return jpaOrderRepository.findById(orderId);
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return jpaOrderRepository.findAll(pageable);
	}

	@Override
	public Order save(Order order) {
		return jpaOrderRepository.save(order);
	}

	@Override
	public void deleteById(UUID orderId) {
		jpaOrderRepository.deleteById(orderId);
	}
}
