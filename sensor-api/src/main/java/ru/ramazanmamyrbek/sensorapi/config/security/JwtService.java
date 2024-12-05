package ru.ramazanmamyrbek.sensorapi.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration-millis}")
    private int jwtExpirationMillis;
    SecretKey secretKey;

    @PostConstruct
    public void initKey() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(new Date().getTime()+jwtExpirationMillis))
                .issuer("sensor-api")
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        Claims claims = parseClaims(token);
        return !claims.getExpiration().before(new Date()) && claims.getIssuer().equals("sensor-api");
    }
    private Claims parseClaims(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;

    }

    public String getUsername(String token) {
        return parseClaims(token).getSubject();
    }
}
