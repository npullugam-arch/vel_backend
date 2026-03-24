package com.veltrixix.veltrixix_backend.dto.dashboard;

public class DashboardSummaryResponse {

    private long totalInternships;
    private long totalEvents;
    private long totalProjects;
    private long totalRegistrations;
    private long approvedRegistrations;
    private long pendingPayments;
    private long verifiedPayments;
    private long totalContactInquiries;

    public DashboardSummaryResponse() {
    }

    public long getTotalInternships() {
        return totalInternships;
    }

    public void setTotalInternships(long totalInternships) {
        this.totalInternships = totalInternships;
    }

    public long getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(long totalEvents) {
        this.totalEvents = totalEvents;
    }

    public long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(long totalProjects) {
        this.totalProjects = totalProjects;
    }

    public long getTotalRegistrations() {
        return totalRegistrations;
    }

    public void setTotalRegistrations(long totalRegistrations) {
        this.totalRegistrations = totalRegistrations;
    }

    public long getApprovedRegistrations() {
        return approvedRegistrations;
    }

    public void setApprovedRegistrations(long approvedRegistrations) {
        this.approvedRegistrations = approvedRegistrations;
    }

    public long getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(long pendingPayments) {
        this.pendingPayments = pendingPayments;
    }

    public long getVerifiedPayments() {
        return verifiedPayments;
    }

    public void setVerifiedPayments(long verifiedPayments) {
        this.verifiedPayments = verifiedPayments;
    }

    public long getTotalContactInquiries() {
        return totalContactInquiries;
    }

    public void setTotalContactInquiries(long totalContactInquiries) {
        this.totalContactInquiries = totalContactInquiries;
    }
}