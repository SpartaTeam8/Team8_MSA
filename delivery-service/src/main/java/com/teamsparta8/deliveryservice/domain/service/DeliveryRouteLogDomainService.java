package com.teamsparta8.deliveryservice.domain.service;

import com.teamsparta8.deliveryservice.domain.model.DeliveryRouteLog;
import com.teamsparta8.deliveryservice.domain.repository.DeliveryRouteLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryRouteLogDomainService {

    private final DeliveryRouteLogRepository deliveryRouteLogRepository;

    public List<DeliveryRouteLog> getDeliveryRouteLogs(UUID deliveryId) {

        return deliveryRouteLogRepository.findAllByDeliveryId(deliveryId);
    }
}
