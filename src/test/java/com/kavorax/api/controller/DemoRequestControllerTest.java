package com.kavorax.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kavorax.api.config.SecurityConfig;
import com.kavorax.api.dto.DemoRequestDto;
import com.kavorax.api.entity.DemoRequest;
import com.kavorax.api.security.JwtAuthenticationFilter;
import com.kavorax.api.security.JwtService;
import com.kavorax.api.service.DemoRequestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoRequestController.class)
@Import({SecurityConfig.class, JwtAuthenticationFilter.class})
public class DemoRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DemoRequestService demoRequestService;

    @MockitoBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSubmitDemoRequestSuccess() throws Exception {
        DemoRequestDto request = new DemoRequestDto();
        request.setName("Jane Doe");
        request.setEmail("jane.doe@example.com");
        request.setCompany("Acme Corp");
        request.setRequirements("SSO support");
        request.setPhone("+1-555-0100");
        request.setDate("2026-11-01");
        request.setTimeSlot("10:00 AM");

        DemoRequest responseDemo = new DemoRequest();
        responseDemo.setId(1L);
        responseDemo.setName("Jane Doe");
        responseDemo.setEmail("jane.doe@example.com");
        responseDemo.setCompany("Acme Corp");
        responseDemo.setRequirements("SSO support");
        responseDemo.setPhone("+1-555-0100");
        responseDemo.setDate("2026-11-01");
        responseDemo.setTimeSlot("10:00 AM");
        responseDemo.setStatus("PENDING");

        Mockito.when(demoRequestService.createDemoRequest(any(DemoRequestDto.class))).thenReturn(responseDemo);

        mockMvc.perform(post("/api/v1/demo-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.phone").value("+1-555-0100"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void testSubmitDemoValidationFailure() throws Exception {
        DemoRequestDto request = new DemoRequestDto();
        // Missing name and invalid email
        request.setEmail("invalid-email");

        mockMvc.perform(post("/api/v1/demo-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationErrors.name").exists())
                .andExpect(jsonPath("$.validationErrors.email").exists());
    }
}
