package com.teamsparta8.gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)  // CSRF 보호 비활성화
                .authorizeExchange(exchange ->
                        exchange
                                .pathMatchers("/api/auth/sign-up", "/api/auth/sign-in").permitAll()  // /public/** 경로는 인증 없이 접근 허용
                                .anyExchange().authenticated()
                );

        return http.build();
    }
}