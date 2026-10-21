package com.kavorax.api.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretString", "test-secret-key-that-is-at-least-256-bits-long-for-hmac-sha-256!");
        ReflectionTestUtils.setField(jwtService, "expirationMs", 3600000L); // 1 hour
        jwtService.init();
    }

    @Test
    public void testGenerateAndValidateToken() {
        String token = jwtService.generateToken("admin@kavorax.com", 1L, "ADMIN");

        assertNotNull(token);
        assertTrue(jwtService.validateToken(token));
        assertEquals("admin@kavorax.com", jwtService.extractUsername(token));
        assertEquals(1L, jwtService.extractUserId(token));
        assertEquals("ADMIN", jwtService.extractRole(token));
    }

    @Test
    public void testTamperedTokenFailsValidation() {
        String token = jwtService.generateToken("admin@kavorax.com", 1L, "ADMIN");
        String tampered = token.substring(0, token.length() - 5) + "abcde";

        assertFalse(jwtService.validateToken(tampered));
    }

    @Test
    public void testExpiredTokenFailsValidation() {
        JwtService shortLivedJwtService = new JwtService();
        ReflectionTestUtils.setField(shortLivedJwtService, "secretString", "test-secret-key-that-is-at-least-256-bits-long-for-hmac-sha-256!");
        ReflectionTestUtils.setField(shortLivedJwtService, "expirationMs", -1000L); // Expired immediately
        shortLivedJwtService.init();

        String expiredToken = shortLivedJwtService.generateToken("admin@kavorax.com", 1L, "ADMIN");

        assertFalse(shortLivedJwtService.validateToken(expiredToken));
        assertTrue(shortLivedJwtService.isTokenExpired(expiredToken));
    }
}
