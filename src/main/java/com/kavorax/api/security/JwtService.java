package com.kavorax.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    // 256-bit default secret for isolated development/test environments
    private static final String DEV_DEFAULT_SECRET = "kavorax-super-secret-key-for-jwt-signing-minimum-256-bits-2025!";

    @Value("${jwt.secret:${JWT_SECRET:}}")
    private String secretString;

    @Value("${jwt.expiration-ms:86400000}") // 24 hours by default
    private long expirationMs;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        String keyToUse = secretString;
        if (keyToUse == null || keyToUse.trim().isEmpty()) {
            logger.warn("No JWT_SECRET provided in environment. Utilizing development fallback signing key. MUST be set in production!");
            keyToUse = DEV_DEFAULT_SECRET;
        }

        byte[] keyBytes = keyToUse.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            // Pad or throw if secret is too short for HS256
            logger.warn("JWT secret is under 256 bits (32 bytes). Padding to meet HMAC-SHA-256 security specifications.");
            byte[] padded = new byte[32];
            System.arraycopy(keyBytes, 0, padded, 0, Math.min(keyBytes.length, 32));
            this.signingKey = Keys.hmacShaKeyFor(padded);
        } else {
            this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        }
    }

    public String generateToken(String email, Long userId, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role != null ? role : "ADMIN");

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        Object idVal = extractAllClaims(token).get("userId");
        if (idVal instanceof Number) {
            return ((Number) idVal).longValue();
        }
        return null;
    }

    public String extractRole(String token) {
        Object roleVal = extractAllClaims(token).get("role");
        return roleVal != null ? roleVal.toString() : "ADMIN";
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expiration = extractAllClaims(token).getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            logger.debug("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }
}
