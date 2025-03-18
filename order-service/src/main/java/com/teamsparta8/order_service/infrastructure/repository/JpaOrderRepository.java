package com.teamsparta8.order_service.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsparta8.order_service.domain.model.Order;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, UUID> {
}
