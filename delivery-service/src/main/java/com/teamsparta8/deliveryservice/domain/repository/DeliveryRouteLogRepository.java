package com.teamsparta8.deliveryservice.domain.repository;

import com.teamsparta8.deliveryservice.domain.model.DeliveryRouteLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeliveryRouteLogRepository {

    List<DeliveryRouteLog> findAllByDeliveryId(UUID deliveryId);
}
