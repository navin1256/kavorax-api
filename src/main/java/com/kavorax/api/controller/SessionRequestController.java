package com.kavorax.api.controller;

import com.kavorax.api.dto.DemoRequestDto;
import com.kavorax.api.entity.DemoRequest;
import com.kavorax.api.service.DemoRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/session-requests")
public class SessionRequestController {

    private final DemoRequestService demoRequestService;

    public SessionRequestController(DemoRequestService demoRequestService) {
        this.demoRequestService = demoRequestService;
    }

    @PostMapping
    public ResponseEntity<DemoRequest> submitSessionRequest(@Valid @RequestBody DemoRequestDto request) {
        if (request.getType() == null || request.getType().trim().isEmpty()) {
            request.setType("SESSION_REQUEST");
        }
        DemoRequest created = demoRequestService.createDemoRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
