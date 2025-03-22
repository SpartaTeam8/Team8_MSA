package com.teamsparta8.order_service.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.teamsparta8.order_service.presentatin.dto.CreateDeliveryRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateDeliveryResponse;

@FeignClient(name = "delivery-service", url = "http://delivery-service")
public interface DeliveryClient {
	@PostMapping("/api/deliveries")
	CreateDeliveryResponse createDelivery(@RequestBody CreateDeliveryRequest request);

}
