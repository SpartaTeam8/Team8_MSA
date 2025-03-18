package com.sparta.user.presentation.util;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil{
    private static final String SECRET_KEY = "c2VjcmV0X2tleV9zZWNyZXRfa2V5X3NlY3JldF9rZXk=";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
    Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    // jwt 토큰 생성
    public String generateToken(String username){
        return Jwts.builder()
                .claim("sub", username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // 토큰에서 사용자 추출
    public String getUsernameFromToken(String token){
        return parseClaims(token).getSubject();
    }

    // 토큰이 만료되었는지 확인
    public boolean isTokenExpired(String token){
        return parseClaims(token).getExpiration().before(new Date());
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 토큰 검증
    public boolean validateToken(String token, String username) {
        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
    }

}
