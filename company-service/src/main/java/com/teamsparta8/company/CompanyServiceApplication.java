package com.teamsparta8.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient  // Eureka Client 활성화
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 자동 DB 설정 제외-일시적으로 설정해둠.

public class CompanyServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(CompanyServiceApplication.class, args);
  }
}