package com.teamsparta8.gateway_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 검증
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()  // parser 사용
                    .verifyWith((SecretKey) key)  // 서명 검증
                    .build()  // 빌드 후 실행
                    .parseSignedClaims(token)  // 서명된 JWT 파싱
                    .getPayload();
            return true; // 유효한 토큰
        } catch (Exception e) {
            return false; // 유효하지 않은 토큰
        }
    }
}

