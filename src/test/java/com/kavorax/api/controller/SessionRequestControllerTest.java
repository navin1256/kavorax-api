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

@WebMvcTest(SessionRequestController.class)
@Import({SecurityConfig.class, JwtAuthenticationFilter.class})
public class SessionRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DemoRequestService demoRequestService;

    @MockitoBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSubmitSessionRequestSuccess() throws Exception {
        DemoRequestDto request = new DemoRequestDto();
        request.setName("Robert Taylor");
        request.setEmail("robert@example.com");
        request.setCompany("Taylor Retail");
        request.setPhone("555-8899");
        request.setDate("2026-11-15");
        request.setTimeSlot("02:00 PM - 03:00 PM");

        DemoRequest responseDemo = new DemoRequest();
        responseDemo.setId(10L);
        responseDemo.setType("SESSION_REQUEST");
        responseDemo.setName("Robert Taylor");
        responseDemo.setEmail("robert@example.com");
        responseDemo.setCompany("Taylor Retail");
        responseDemo.setPhone("555-8899");
        responseDemo.setDate("2026-11-15");
        responseDemo.setTimeSlot("02:00 PM - 03:00 PM");
        responseDemo.setStatus("PENDING");

        Mockito.when(demoRequestService.createDemoRequest(any(DemoRequestDto.class))).thenReturn(responseDemo);

        mockMvc.perform(post("/api/v1/session-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.type").value("SESSION_REQUEST"))
                .andExpect(jsonPath("$.timeSlot").value("02:00 PM - 03:00 PM"));
    }
}
