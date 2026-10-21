package com.kavorax.api.controller.admin;

import com.kavorax.api.dto.DashboardStatsDto;
import com.kavorax.api.repository.ContactMessageRepository;
import com.kavorax.api.repository.DemoRequestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
public class AdminDashboardController {

    private final DemoRequestRepository demoRequestRepository;
    private final ContactMessageRepository contactMessageRepository;

    public AdminDashboardController(DemoRequestRepository demoRequestRepository,
                                    ContactMessageRepository contactMessageRepository) {
        this.demoRequestRepository = demoRequestRepository;
        this.contactMessageRepository = contactMessageRepository;
    }

    @GetMapping("/stats")
    public DashboardStatsDto getDashboardStats() {
        long totalDemoBookings = demoRequestRepository.countByType("DEMO_BOOKING");
        long totalSessionRequests = demoRequestRepository.countByType("SESSION_REQUEST");
        long legacyEnquiries = demoRequestRepository.countByType("ENQUIRY");
        long totalContactMessages = contactMessageRepository.count();

        // Enquiries include both legacy migrated records and active contact messages
        long totalEnquiries = legacyEnquiries + totalContactMessages;
        long totalDemoLeads = demoRequestRepository.count() + totalContactMessages;

        long newLeads = demoRequestRepository.countByStatus("NEW") + contactMessageRepository.countByStatus("NEW");
        long pendingLeads = demoRequestRepository.countByStatus("PENDING") + contactMessageRepository.countByStatus("RECEIVED");

        return new DashboardStatsDto(totalDemoLeads, totalDemoBookings, totalSessionRequests, totalEnquiries, newLeads, pendingLeads, totalContactMessages);
    }
}
