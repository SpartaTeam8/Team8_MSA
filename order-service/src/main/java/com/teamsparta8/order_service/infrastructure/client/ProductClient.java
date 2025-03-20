package com.teamsparta8.order_service.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service", path = "/api/products")
public class ProductClient {
}
