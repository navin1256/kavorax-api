package com.kavorax.api.controller.admin;

import com.kavorax.api.dto.AdminContactMessageResponse;
import com.kavorax.api.entity.ContactMessage;
import com.kavorax.api.repository.ContactMessageRepository;
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
@RequestMapping({"/api/v1/admin/contact-messages", "/api/v1/admin/enquiries"})
public class AdminContactMessageController {

    private final ContactMessageRepository contactMessageRepository;

    public AdminContactMessageController(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @GetMapping
    public ResponseEntity<List<AdminContactMessageResponse>> getAllContactMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        int pageSize = Math.max(1, Math.min(size, 100));
        int pageNumber = Math.max(0, page);
        String safeSort = "name".equalsIgnoreCase(sortBy) || "email".equalsIgnoreCase(sortBy) ? sortBy : "createdAt";
        Sort sort = "asc".equalsIgnoreCase(direction) ? Sort.by(safeSort).ascending() : Sort.by(safeSort).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<ContactMessage> pagedResult = contactMessageRepository.findAll(pageable);

        List<AdminContactMessageResponse> response = pagedResult.getContent().stream().map(msg -> {
            AdminContactMessageResponse dto = new AdminContactMessageResponse();
            dto.setId(msg.getId());
            dto.setName(msg.getName());
            dto.setEmail(msg.getEmail());
            dto.setPhone(msg.getPhone());
            dto.setSubject(msg.getSubject());
            dto.setMessage(msg.getMessage());
            dto.setStatus(msg.getStatus());
            dto.setCreatedAt(msg.getCreatedAt());
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
