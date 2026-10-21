package com.kavorax.api.controller;

import com.kavorax.api.dto.DemoRequestDto;
import com.kavorax.api.entity.DemoRequest;
import com.kavorax.api.service.DemoRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-requests")
public class DemoRequestController {

    private final DemoRequestService demoRequestService;

    public DemoRequestController(DemoRequestService demoRequestService) {
        this.demoRequestService = demoRequestService;
    }

    @PostMapping
    public ResponseEntity<DemoRequest> submitDemoRequest(@Valid @RequestBody DemoRequestDto request) {
        DemoRequest createdDemoRequest = demoRequestService.createDemoRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDemoRequest);
    }
}
