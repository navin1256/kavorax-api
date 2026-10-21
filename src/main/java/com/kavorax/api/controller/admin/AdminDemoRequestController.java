package com.kavorax.api.controller.admin;

import com.kavorax.api.dto.AdminDemoRequestResponse;
import com.kavorax.api.entity.DemoRequest;
import com.kavorax.api.repository.DemoRequestRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/v1/admin/demo-leads", "/api/v1/admin/demo-requests", "/api/v1/admin/session-requests"})
public class AdminDemoRequestController {

    private final DemoRequestRepository demoRequestRepository;

    public AdminDemoRequestController(DemoRequestRepository demoRequestRepository) {
        this.demoRequestRepository = demoRequestRepository;
    }

    @GetMapping
    public ResponseEntity<List<AdminDemoRequestResponse>> getAllDemoRequests(
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            HttpServletRequest request) {

        String requestUri = request.getRequestURI();
        String effectiveType = type;

        // Auto-filter based on canonical endpoint URI if type is not explicitly provided
        if (effectiveType == null || effectiveType.trim().isEmpty()) {
            if (requestUri.endsWith("/demo-requests")) {
                effectiveType = "DEMO_BOOKING";
            } else if (requestUri.endsWith("/session-requests")) {
                effectiveType = "SESSION_REQUEST";
            }
        }

        int pageSize = Math.max(1, Math.min(size, 100));
        int pageNumber = Math.max(0, page);
        String safeSort = "name".equalsIgnoreCase(sortBy) || "email".equalsIgnoreCase(sortBy) ? sortBy : "createdAt";
        Sort sort = "asc".equalsIgnoreCase(direction) ? Sort.by(safeSort).ascending() : Sort.by(safeSort).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<DemoRequest> pagedResult;
        if (effectiveType != null && !effectiveType.trim().isEmpty()) {
            pagedResult = demoRequestRepository.findByTypeIgnoreCase(effectiveType.trim(), pageable);
        } else {
            pagedResult = demoRequestRepository.findAll(pageable);
        }

        List<AdminDemoRequestResponse> response = pagedResult.getContent().stream().map(req -> {
            AdminDemoRequestResponse dto = new AdminDemoRequestResponse();
            dto.setId(req.getId());
            dto.setType(req.getType());
            dto.setName(req.getName());
            dto.setEmail(req.getEmail());
            dto.setPhone(req.getPhone());
            dto.setCompany(req.getCompany());
            dto.setRequirements(req.getRequirements());
            dto.setMessage(req.getMessage());
            dto.setDate(req.getDate());
            dto.setTimeSlot(req.getTimeSlot());
            dto.setStatus(req.getStatus());
            dto.setCreatedAt(req.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(pagedResult.getTotalElements()))
                .header("X-Total-Pages", String.valueOf(pagedResult.getTotalPages()))
                .header("X-Current-Page", String.valueOf(pageNumber))
                .header("X-Page-Size", String.valueOf(pageSize))
                .body(response);
    }
}
