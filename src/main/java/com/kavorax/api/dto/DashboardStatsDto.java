package com.kavorax.api.dto;

public class DashboardStatsDto {
    private long totalDemoLeads;
    private long totalDemoBookings;
    private long totalSessionRequests;
    private long totalEnquiries;
    private long newLeads;
    private long pendingLeads;
    private long totalContactMessages;

    public DashboardStatsDto() {}

    public DashboardStatsDto(long totalDemoLeads, long totalDemoBookings, long totalSessionRequests, long totalEnquiries, long newLeads, long pendingLeads) {
        this.totalDemoLeads = totalDemoLeads;
        this.totalDemoBookings = totalDemoBookings;
        this.totalSessionRequests = totalSessionRequests;
        this.totalEnquiries = totalEnquiries;
        this.newLeads = newLeads;
        this.pendingLeads = pendingLeads;
        this.totalContactMessages = totalEnquiries;
    }

    public DashboardStatsDto(long totalDemoLeads, long totalDemoBookings, long totalSessionRequests, long totalEnquiries, long newLeads, long pendingLeads, long totalContactMessages) {
        this.totalDemoLeads = totalDemoLeads;
        this.totalDemoBookings = totalDemoBookings;
        this.totalSessionRequests = totalSessionRequests;
        this.totalEnquiries = totalEnquiries;
        this.newLeads = newLeads;
        this.pendingLeads = pendingLeads;
        this.totalContactMessages = totalContactMessages;
    }

    public long getTotalDemoLeads() { return totalDemoLeads; }
    public void setTotalDemoLeads(long totalDemoLeads) { this.totalDemoLeads = totalDemoLeads; }

    public long getTotalDemoBookings() { return totalDemoBookings; }
    public void setTotalDemoBookings(long totalDemoBookings) { this.totalDemoBookings = totalDemoBookings; }

    public long getTotalSessionRequests() { return totalSessionRequests; }
    public void setTotalSessionRequests(long totalSessionRequests) { this.totalSessionRequests = totalSessionRequests; }

    public long getTotalEnquiries() { return totalEnquiries; }
    public void setTotalEnquiries(long totalEnquiries) { this.totalEnquiries = totalEnquiries; }

    public long getNewLeads() { return newLeads; }
    public void setNewLeads(long newLeads) { this.newLeads = newLeads; }

    public long getPendingLeads() { return pendingLeads; }
    public void setPendingLeads(long pendingLeads) { this.pendingLeads = pendingLeads; }

    public long getTotalContactMessages() { return totalContactMessages; }
    public void setTotalContactMessages(long totalContactMessages) { this.totalContactMessages = totalContactMessages; }
}
