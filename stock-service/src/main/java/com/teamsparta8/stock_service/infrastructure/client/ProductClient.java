// package com.teamsparta8.stock_service.infrastructure.client;
//
// import java.util.UUID;
//
// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
//
// @FeignClient(name = "product-service", url = "http://product-service")
// public class ProductClient {
//
// 	// 상품 존재 여부 확인
// 	@GetMapping("/api/products/{productId}")
// 	ProductResponse getProductDetails(@PathVariable UUID productId);
//
// 	// 상품 가격 조회
// 	@GetMapping("/api/products/price")
// 	int getProductPrice(@RequestParam UUID productId);
// }
