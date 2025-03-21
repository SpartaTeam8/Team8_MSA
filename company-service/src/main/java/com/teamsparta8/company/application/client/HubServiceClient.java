/*
package com.teamsparta8.company.application.client;



import com.teamsparta8.hub.application.dto.HubResponseInternalDto;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service", url = "http://localhost:8081") // hub-service가 8081에서 실행된다고 가정한 것이므로, 실제 실행 포트에 맞게 변경 필요!url = "http://hub-service"
public interface HubServiceClient {
  @GetMapping("/api/hubs/{hubId}") //허브 상세 조회 API 호출
  HubResponseInternalDto getHubById(@PathVariable("hubId") UUID hubId);

  @GetMapping("/hubs/{hubId}/exists")  // 허브 존재 여부 확인 API
  boolean checkHubExists(@PathVariable UUID hubId);
}*/
