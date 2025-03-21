package com.teamsparta8.deliveryservice.domain.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsparta8.deliveryservice.domain.model.Delivery;
import com.teamsparta8.deliveryservice.domain.repository.DeliveryRepository;
import com.teamsparta8.deliveryservice.infrastructure.repository.DeliveryQueryDSLRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryDomainService {

	private final DeliveryRepository deliveryRepository;

	private final DeliveryQueryDSLRepositoryImpl deliveryQueryDSLRepository;

	@Transactional
	public void createDelivery(Delivery delivery) {

		if(isDuplicated(delivery)) {
			throw new IllegalArgumentException("중복된 배송 정보입니다.");
		}

		deliveryRepository.save(delivery);
	}

	@Transactional(readOnly = true)
	public Delivery readDelivery(UUID deliveryId) {

		return deliveryRepository.findById(deliveryId);
	}

	@Transactional(readOnly = true)
	public Page<Delivery> searchDeliveriesByDepartureHubId(UUID departureHubId, Pageable pageable) {

		return deliveryQueryDSLRepository.searchByDepartureHubId(departureHubId, pageable);
	}

	@Transactional
	public void updateDelivery(Delivery delivery) {

		deliveryRepository.save(delivery);
	}

	@Transactional
	public void deleteDelivery(Delivery delivery) {

		deliveryRepository.save(delivery);
	}

	public boolean isDuplicated(Delivery delivery) {

		return deliveryRepository.existsByOrderId(delivery.getOrderId());
	}
}
