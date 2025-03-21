package com.teamsparta8.deliveryservice.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.teamsparta8.deliveryservice.domain.model.Delivery;
import com.teamsparta8.deliveryservice.domain.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {
	private final JpaDeliveryRepository jpaDeliveryRepository;

	@Override
	public void save(Delivery delivery) {

		jpaDeliveryRepository.save(delivery);
	}

	@Override
	public Delivery findById(UUID deliveryId) {

		return jpaDeliveryRepository.findById(deliveryId)
			.orElseThrow(() -> new IllegalArgumentException("조회된 배송 정보가 없습니다 : " + deliveryId));
	}

	@Override
	public boolean existsByOrderId(UUID orderId) {
		return jpaDeliveryRepository.existsByOrderId(orderId);
	}
}
