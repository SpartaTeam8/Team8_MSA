package com.teamsparta8.deliveryservice.application.service;

import com.teamsparta8.deliveryservice.application.dto.DeliveryRouteLogResponseInternalDto;
import com.teamsparta8.deliveryservice.application.util.DeliveryRouteLogMapper;
import com.teamsparta8.deliveryservice.domain.model.DeliveryRouteLog;
import com.teamsparta8.deliveryservice.domain.service.DeliveryRouteLogDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryRouteLogService {

    private final DeliveryRouteLogDomainService deliveryRouteLogDomainService;

    private final DeliveryRouteLogMapper deliveryRouteLogMapper;

    public List<DeliveryRouteLogResponseInternalDto> getDeliveryRouteLog(UUID deliveryId) {

        List<DeliveryRouteLog> deliveryRouteLogList = deliveryRouteLogDomainService.getDeliveryRouteLogs(deliveryId);

        return deliveryRouteLogMapper.toResponseList(deliveryRouteLogList);
    }
}
