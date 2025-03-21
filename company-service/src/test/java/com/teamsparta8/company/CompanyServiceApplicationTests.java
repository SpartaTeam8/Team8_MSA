package com.teamsparta8.company;

import com.teamsparta8.company.application.service.CompanyService;
import com.teamsparta8.company.config.TestConfig;
import com.teamsparta8.company.domain.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest  // 스프링 컨텍스트를 로드하여 테스트 실행
@TestPropertySource(properties = {
    "eureka.client.enabled=false",
    "eureka.client.register-with-eureka=false",
    "eureka.client.fetch-registry=false"
})
public class CompanyServiceApplicationTests {
  @Mock
  private CompanyRepository companyRepository; // Repository Mocking

  @InjectMocks
  private CompanyService companyService; // Service Mocking

  @BeforeEach
  void setUp() {
    // 필요시 Mockito 초기화 작업 가능
  }

  @Test
  void contextLoads() {
    // 애플리케이션 컨텍스트가 정상적으로 로드되는지 확인하는 테스트
  }
}