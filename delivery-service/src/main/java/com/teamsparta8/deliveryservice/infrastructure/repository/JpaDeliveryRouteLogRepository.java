package com.teamsparta8.deliveryservice.infrastructure.repository;

import com.teamsparta8.deliveryservice.domain.model.DeliveryRouteLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaDeliveryRouteLogRepository extends JpaRepository<DeliveryRouteLog, UUID> {

    List<DeliveryRouteLog> findAllByDeliveryId(UUID deliveryId);
}
