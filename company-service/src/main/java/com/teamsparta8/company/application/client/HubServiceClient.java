package com.teamsparta8.company.application.client;

import com.teamsparta8.hub.presentation.dto.hub.HubResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service", url = "http://hub-service") // Eureka 사용 시 서비스명
public interface HubServiceClient {
  @GetMapping("/api/hubs/{hubId}") //허브 상세 조회 API 호출
  HubResponseDto getHubById(@PathVariable("hubId") UUID hubId);

  @GetMapping("/hubs/{hubId}/exists")  // 허브 존재 여부 확인 API
  boolean checkHubExists(@PathVariable UUID hubId);
}