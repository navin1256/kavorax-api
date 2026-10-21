package com.kavorax.api.controller;

import com.kavorax.api.dto.ContactMessageRequest;
import com.kavorax.api.entity.ContactMessage;
import com.kavorax.api.service.ContactMessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/v1/contact-messages", "/api/v1/enquiries"})
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping
    public ResponseEntity<ContactMessage> submitContactMessage(@Valid @RequestBody ContactMessageRequest request) {
        ContactMessage createdContactMessage = contactMessageService.createContactMessage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContactMessage);
    }
}
