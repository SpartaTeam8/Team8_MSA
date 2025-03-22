package com.teamsparta8.company.config;

import com.teamsparta8.company.application.service.CompanyService;
import com.teamsparta8.company.domain.repository.CompanyRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
  @Bean
  public CompanyService companyService() {
    return Mockito.mock(CompanyService.class);
  }

  @Bean
  public CompanyRepository companyRepository() {
    return Mockito.mock(CompanyRepository.class);
  }

}
