package com.teamsparta8.deliveryservice.infrastructure.repository;

import com.teamsparta8.deliveryservice.domain.model.Delivery;
import com.teamsparta8.deliveryservice.domain.model.DeliveryRouteLog;
import com.teamsparta8.deliveryservice.domain.repository.DeliveryRepository;
import com.teamsparta8.deliveryservice.domain.repository.DeliveryRouteLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeliveryRouteLogRepositoryImpl implements DeliveryRouteLogRepository {

    private final JpaDeliveryRouteLogRepository jpaDeliveryRouteLogRepository;

    @Override
    public List<DeliveryRouteLog> findAllByDeliveryId(UUID deliveryId) {

        return jpaDeliveryRouteLogRepository.findAllByDeliveryId(deliveryId);
    }
}
