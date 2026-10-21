package com.kavorax.api.service;

import com.kavorax.api.dto.DemoRequestDto;
import com.kavorax.api.entity.DemoRequest;
import com.kavorax.api.repository.DemoRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoRequestService {

    private final DemoRequestRepository demoRequestRepository;

    public DemoRequestService(DemoRequestRepository demoRequestRepository) {
        this.demoRequestRepository = demoRequestRepository;
    }

    @Transactional
    public DemoRequest createDemoRequest(DemoRequestDto requestDto) {
        DemoRequest demoRequest = new DemoRequest();
        demoRequest.setName(requestDto.getName().trim());
        demoRequest.setEmail(requestDto.getEmail().trim().toLowerCase());
        demoRequest.setCompany(requestDto.getCompany());
        demoRequest.setRequirements(requestDto.getRequirements());
        demoRequest.setPhone(requestDto.getPhone());
        demoRequest.setDate(requestDto.getDate());
        demoRequest.setTimeSlot(requestDto.getTimeSlot());
        demoRequest.setMessage(requestDto.getMessage());

        if (requestDto.getType() != null && !requestDto.getType().trim().isEmpty()) {
            demoRequest.setType(requestDto.getType().trim().toUpperCase());
        } else {
            demoRequest.setType("DEMO_BOOKING");
        }

        return demoRequestRepository.save(demoRequest);
    }
}
