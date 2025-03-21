package com.teamsparta8.gateway_service.Filter;

import com.teamsparta8.gateway_service.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtTokenProvider jwtTokenProvider; // JWT 검증 유틸리티

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if(path.startsWith("/api/auth")){
            System.out.println("✅ 인증이 필요 없는 경로이므로 필터 통과");
            return chain.filter(exchange);
        }

        ServerHttpRequest request = exchange.getRequest();

        // 1️⃣ Authorization 헤더 확인
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            System.out.println("❌ Authorization 헤더가 없습니다.");
            return onError(exchange, "Missing Authorization Header", HttpStatus.UNAUTHORIZED);
        }

        // 2️⃣ Bearer 토큰 추출
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        System.out.println("✅ Authorization 헤더 값: " + token);
        if (token == null || !token.startsWith("Bearer ")) {
            System.out.println("❌ Authorization 헤더 형식이 잘못되었습니다.");
            return onError(exchange, "Invalid Authorization Header", HttpStatus.UNAUTHORIZED);
        }

        token = token.replace("Bearer ", ""); // "Bearer " 부분 제거
        System.out.println("✅ 추출된 JWT 토큰: " + token);

        // 3️⃣ JWT 검증
        if (!jwtTokenProvider.validateToken(token)  ) {
            System.out.println("❌ JWT 토큰 검증 실패");
            return onError(exchange, "Invalid JWT Token", HttpStatus.UNAUTHORIZED);
        }

        // 4️⃣ 요청을 그대로 다음 필터로 전달
        System.out.println("✅ JWT 검증 성공");
        return chain.filter(exchange);
    }

    // 인증 실패 시 응답 처리
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1; // 필터의 우선순위 설정 (낮을수록 먼저 실행)
    }
}
