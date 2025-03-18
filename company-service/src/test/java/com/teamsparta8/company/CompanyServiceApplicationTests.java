package com.teamsparta8.company;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest  // 스프링 컨텍스트를 로드하여 테스트 실행
@TestPropertySource(properties = {
    "eureka.client.enabled=false"  // 테스트 시 Eureka Client 비활성화
})
public class CompanyServiceApplicationTests {

  @Test
  void contextLoads() {
    // 애플리케이션 컨텍스트가 정상적으로 로드되는지 확인하는 기본 테스트
  }
}