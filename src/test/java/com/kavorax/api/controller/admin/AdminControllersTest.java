package com.kavorax.api.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kavorax.api.config.SecurityConfig;
import com.kavorax.api.entity.ContactMessage;
import com.kavorax.api.entity.DemoRequest;
import com.kavorax.api.repository.ContactMessageRepository;
import com.kavorax.api.repository.DemoRequestRepository;
import com.kavorax.api.security.JwtAuthenticationFilter;
import com.kavorax.api.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AdminDashboardController.class, AdminContactMessageController.class, AdminDemoRequestController.class})
@Import({SecurityConfig.class, JwtAuthenticationFilter.class})
public class AdminControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DemoRequestRepository demoRequestRepository;

    @MockitoBean
    private ContactMessageRepository contactMessageRepository;

    @MockitoBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String validToken = "valid.jwt.token";

    @BeforeEach
    public void setup() {
        Mockito.when(jwtService.validateToken(validToken)).thenReturn(true);
        Mockito.when(jwtService.extractUsername(validToken)).thenReturn("admin@kavorax.com");
        Mockito.when(jwtService.extractRole(validToken)).thenReturn("ADMIN");

        Mockito.when(jwtService.validateToken("invalid.token")).thenReturn(false);
    }

    @Test
    public void testAdminEndpointWithoutTokenReturnsUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/admin/dashboard/stats"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.error").value("Unauthorized"));
    }

    @Test
    public void testAdminEndpointWithInvalidTokenReturnsUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/admin/dashboard/stats")
                .header("Authorization", "Bearer invalid.token"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401));
    }

    @Test
    public void testDashboardStatsWithValidTokenReturnsMetrics() throws Exception {
        Mockito.when(demoRequestRepository.count()).thenReturn(10L);
        Mockito.when(demoRequestRepository.countByType("DEMO_BOOKING")).thenReturn(5L);
        Mockito.when(demoRequestRepository.countByType("SESSION_REQUEST")).thenReturn(3L);
        Mockito.when(demoRequestRepository.countByType("ENQUIRY")).thenReturn(2L);
        Mockito.when(contactMessageRepository.count()).thenReturn(8L);
        Mockito.when(demoRequestRepository.countByStatus("NEW")).thenReturn(2L);
        Mockito.when(contactMessageRepository.countByStatus("NEW")).thenReturn(1L);
        Mockito.when(demoRequestRepository.countByStatus("PENDING")).thenReturn(8L);
        Mockito.when(contactMessageRepository.countByStatus("RECEIVED")).thenReturn(7L);

        mockMvc.perform(get("/api/v1/admin/dashboard/stats")
                .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalDemoBookings").value(5))
                .andExpect(jsonPath("$.totalSessionRequests").value(3))
                .andExpect(jsonPath("$.totalContactMessages").value(8))
                .andExpect(jsonPath("$.totalEnquiries").value(10)) // 2 legacy + 8 contact messages
                .andExpect(jsonPath("$.totalDemoLeads").value(18)); // 10 demo table + 8 contact messages
    }

    @Test
    public void testGetContactMessagesWithPagination() throws Exception {
        ContactMessage msg = new ContactMessage();
        msg.setId(1L);
        msg.setName("Sarah Connor");
        msg.setEmail("sarah@resistance.org");
        msg.setSubject("General Inquiry");
        msg.setMessage("Need system consultation");
        msg.setStatus("RECEIVED");
        msg.setCreatedAt(LocalDateTime.now());

        Mockito.when(contactMessageRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(msg)));

        mockMvc.perform(get("/api/v1/admin/contact-messages")
                .header("Authorization", "Bearer " + validToken)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Total-Count", "1"))
                .andExpect(header().string("X-Current-Page", "0"))
                .andExpect(jsonPath("$[0].name").value("Sarah Connor"));
    }

    @Test
    public void testGetDemoLeadsWithFilteringAndPagination() throws Exception {
        DemoRequest demo = new DemoRequest();
        demo.setId(1L);
        demo.setType("DEMO_BOOKING");
        demo.setName("John Connor");
        demo.setEmail("john@resistance.org");
        demo.setStatus("PENDING");
        demo.setCreatedAt(LocalDateTime.now());

        Mockito.when(demoRequestRepository.findByTypeIgnoreCase(eq("DEMO_BOOKING"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(demo)));

        mockMvc.perform(get("/api/v1/admin/demo-requests")
                .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Total-Count", "1"))
                .andExpect(jsonPath("$[0].type").value("DEMO_BOOKING"))
                .andExpect(jsonPath("$[0].name").value("John Connor"));
    }
}
