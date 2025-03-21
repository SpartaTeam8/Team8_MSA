package com.teamsparta8.stock_service.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamsparta8.stock_service.domain.model.Stock;

public interface JpaStockRepository extends JpaRepository<Stock, UUID> {
	Optional<Stock> findByProductId(UUID productId);
	Optional<Stock> findByHubIdAndProductId(UUID hubId, UUID productId);
}
