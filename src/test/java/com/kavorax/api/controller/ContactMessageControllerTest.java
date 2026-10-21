package com.kavorax.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kavorax.api.config.SecurityConfig;
import com.kavorax.api.dto.ContactMessageRequest;
import com.kavorax.api.entity.ContactMessage;
import com.kavorax.api.security.JwtAuthenticationFilter;
import com.kavorax.api.security.JwtService;
import com.kavorax.api.service.ContactMessageService;
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

@WebMvcTest(ContactMessageController.class)
@Import({SecurityConfig.class, JwtAuthenticationFilter.class})
public class ContactMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContactMessageService contactMessageService;

    @MockitoBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSubmitContactMessageSuccess() throws Exception {
        ContactMessageRequest request = new ContactMessageRequest();
        request.setName("John Doe");
        request.setEmail("john.doe@example.com");
        request.setMessage("Hello world");

        ContactMessage responseContactMessage = new ContactMessage();
        responseContactMessage.setId(1L);
        responseContactMessage.setName("John Doe");
        responseContactMessage.setEmail("john.doe@example.com");
        responseContactMessage.setMessage("Hello world");
        responseContactMessage.setStatus("RECEIVED");

        Mockito.when(contactMessageService.createContactMessage(any(ContactMessageRequest.class))).thenReturn(responseContactMessage);

        mockMvc.perform(post("/api/v1/contact-messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("RECEIVED"));
    }

    @Test
    public void testSubmitEnquiryBackwardCompatibilitySuccess() throws Exception {
        ContactMessageRequest request = new ContactMessageRequest();
        request.setName("Alice Smith");
        request.setEmail("alice@example.com");
        request.setMessage("Legacy enquiry test");

        ContactMessage response = new ContactMessage();
        response.setId(2L);
        response.setName("Alice Smith");
        response.setEmail("alice@example.com");
        response.setMessage("Legacy enquiry test");
        response.setStatus("RECEIVED");

        Mockito.when(contactMessageService.createContactMessage(any(ContactMessageRequest.class))).thenReturn(response);

        // Verify that calling the legacy /api/v1/enquiries endpoint succeeds with 201 Created
        mockMvc.perform(post("/api/v1/enquiries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.status").value("RECEIVED"));
    }

    @Test
    public void testSubmitContactMessageValidationFailure_missingName() throws Exception {
        ContactMessageRequest request = new ContactMessageRequest();
        request.setEmail("john.doe@example.com");
        request.setMessage("Hello world");

        mockMvc.perform(post("/api/v1/contact-messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationErrors.name").exists());
    }

    @Test
    public void testSubmitContactMessageValidationFailure_missingMessage() throws Exception {
        ContactMessageRequest request = new ContactMessageRequest();
        request.setName("John Doe");
        request.setEmail("john.doe@example.com");

        mockMvc.perform(post("/api/v1/contact-messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationErrors.message").exists());
    }

    @Test
    public void testSubmitContactMessageValidationFailure_invalidEmail() throws Exception {
        ContactMessageRequest request = new ContactMessageRequest();
        request.setName("John Doe");
        request.setEmail("invalid-email");
        request.setMessage("Hello world");

        mockMvc.perform(post("/api/v1/contact-messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationErrors.email").exists());
    }
}
