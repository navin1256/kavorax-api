package com.kavorax.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kavorax.api.config.SecurityConfig;
import com.kavorax.api.dto.LoginRequest;
import com.kavorax.api.dto.LoginResponse;
import com.kavorax.api.security.JwtAuthenticationFilter;
import com.kavorax.api.security.JwtService;
import com.kavorax.api.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class, JwtAuthenticationFilter.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtService jwtService;

    @Test
    public void testSuccessfulLogin() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@kavorax.com");
        request.setPassword("SecureAuth_2025!");

        LoginResponse.UserData userData = new LoginResponse.UserData(1L, "admin@kavorax.com", "ADMIN");
        LoginResponse response = new LoginResponse("mock.jwt.token", userData);

        Mockito.when(authService.authenticate(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock.jwt.token"))
                .andExpect(jsonPath("$.user.email").value("admin@kavorax.com"));
    }

    @Test
    public void testInvalidPassword() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@kavorax.com");
        request.setPassword("wrong_password");

        Mockito.when(authService.authenticate(any(LoginRequest.class)))
                .thenThrow(new BadCredentialsException("Invalid email or password"));

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.message").value("Invalid email or password"));
    }

    @Test
    public void testValidationFailureMissingPassword() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@kavorax.com");

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationErrors.password").exists());
    }
}
