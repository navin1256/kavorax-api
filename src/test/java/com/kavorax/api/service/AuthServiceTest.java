package com.kavorax.api.service;

import com.kavorax.api.dto.LoginRequest;
import com.kavorax.api.dto.LoginResponse;
import com.kavorax.api.entity.AdminUser;
import com.kavorax.api.repository.AdminUserRepository;
import com.kavorax.api.security.JwtService;
import com.kavorax.api.security.LoginAttemptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class AuthServiceTest {

    private AdminUserRepository adminUserRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private LoginAttemptService loginAttemptService;
    private AuthService authService;

    @BeforeEach
    public void setup() {
        adminUserRepository = Mockito.mock(AdminUserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        jwtService = Mockito.mock(JwtService.class);
        loginAttemptService = new LoginAttemptService();
        ReflectionTestUtils.setField(loginAttemptService, "maxAttempts", 3);
        ReflectionTestUtils.setField(loginAttemptService, "lockDurationMs", 60000L);

        authService = new AuthService(adminUserRepository, passwordEncoder, jwtService, loginAttemptService);

        AdminUser admin = new AdminUser();
        admin.setId(1L);
        admin.setEmail("admin@kavorax.com");
        admin.setPassword("hashed_secret");
        admin.setRole("ADMIN");

        Mockito.when(adminUserRepository.findByEmail("admin@kavorax.com")).thenReturn(Optional.of(admin));
        Mockito.when(adminUserRepository.findByEmail("unknown@kavorax.com")).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.matches("SecureAuth_2025!", "hashed_secret")).thenReturn(true);
        Mockito.when(passwordEncoder.matches("wrong_password", "hashed_secret")).thenReturn(false);
        Mockito.when(jwtService.generateToken("admin@kavorax.com", 1L, "ADMIN")).thenReturn("signed.jwt.token");
    }

    @Test
    public void testSuccessfulAuthentication() {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@kavorax.com");
        request.setPassword("SecureAuth_2025!");

        LoginResponse response = authService.authenticate(request);

        assertNotNull(response);
        assertEquals("signed.jwt.token", response.getToken());
        assertEquals("admin@kavorax.com", response.getUser().getEmail());
    }

    @Test
    public void testInvalidPasswordThrowsBadCredentials() {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@kavorax.com");
        request.setPassword("wrong_password");

        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));
    }

    @Test
    public void testUnknownEmailThrowsBadCredentials() {
        LoginRequest request = new LoginRequest();
        request.setEmail("unknown@kavorax.com");
        request.setPassword("SecureAuth_2025!");

        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));
    }

    @Test
    public void testExcessiveFailedLoginsTriggerLockout() {
        LoginRequest badRequest = new LoginRequest();
        badRequest.setEmail("admin@kavorax.com");
        badRequest.setPassword("wrong_password");

        // 3 failed attempts
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(badRequest));
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(badRequest));
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(badRequest));

        // 4th attempt should be blocked by rate limiter
        assertThrows(LockedException.class, () -> authService.authenticate(badRequest));
    }
}
