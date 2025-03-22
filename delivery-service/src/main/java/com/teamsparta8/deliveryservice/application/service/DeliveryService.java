package com.teamsparta8.deliveryservice.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.teamsparta8.deliveryservice.application.dto.UpdateDeliveryInternalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teamsparta8.deliveryservice.application.dto.CreateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.application.dto.DeliveryResponseInternalDto;
import com.teamsparta8.deliveryservice.application.util.DeliveryMapper;
import com.teamsparta8.deliveryservice.domain.model.Delivery;
import com.teamsparta8.deliveryservice.domain.model.DeliveryStatus;
import com.teamsparta8.deliveryservice.domain.service.DeliveryDomainService;
import com.teamsparta8.deliveryservice.domain.service.HubRoutePort;
import com.teamsparta8.deliveryservice.infrastructure.client.dto.HubRouteResponseDto;
import com.teamsparta8.deliveryservice.presentation.dto.UpdateDeliveryDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryService {

	private final DeliveryDomainService deliveryDomainService;

	private final HubRoutePort hubRoutePort;

	private final DeliveryMapper deliveryMapper;

	public DeliveryResponseInternalDto createDelivery(CreateDeliveryInternalDto internalDto) {

		Delivery delivery = deliveryMapper.createToDelivery(internalDto);

		UUID departureHubId = delivery.getDepartureHubId();

		UUID arrivalHubId = delivery.getArrivalHubId();

		// hub-service 에서 최적경로 탐색하여 응답
		List<HubRouteResponseDto> routeList = hubRoutePort.getRoutes(departureHubId, arrivalHubId);

		// 응답을 List 로 변환하여 delivery 에 set
		delivery.setDeliveryRouteLogs(deliveryMapper.toDeliveryRouteLogList(routeList));

		deliveryDomainService.createDelivery(delivery);

		return deliveryMapper.deliveryToResponse(delivery);
	}

	public DeliveryResponseInternalDto readDelivery(UUID deliveryId) {

		Delivery delivery = deliveryDomainService.readDelivery(deliveryId);

		return deliveryMapper.deliveryToResponse(delivery);
	}

	public Page<DeliveryResponseInternalDto> searchDeliveriesByDepartureHubId(UUID departureHubId, Pageable pageable) {

		Page<Delivery> deliveryPage = deliveryDomainService.searchDeliveriesByDepartureHubId(departureHubId, pageable);

		return deliveryMapper.deliveryPageToResponse(deliveryPage);
	}

	public DeliveryResponseInternalDto updateDelivery(UUID deliveryId, UpdateDeliveryInternalDto request) {

		Delivery delivery = deliveryDomainService.readDelivery(deliveryId);

		if (request.getDeliveryStatus() != null) {
			delivery.setDeliveryStatus(DeliveryStatus.valueOf(request.getDeliveryStatus()));
		} else if (request.getCompanyDeliveryManagerId() != null) {
			delivery.setCompanyDeliveryManagerId(request.getCompanyDeliveryManagerId());
		}

		deliveryDomainService.updateDelivery(delivery);

		return deliveryMapper.deliveryToResponse(delivery);
	}

	public DeliveryResponseInternalDto deleteDelivery(UUID deliveryId, String username) {

		Delivery delivery = deliveryDomainService.readDelivery(deliveryId);

		delivery.delete(LocalDateTime.now(), username);

		deliveryDomainService.deleteDelivery(delivery);

		return deliveryMapper.deliveryToResponse(delivery);
	}
}
