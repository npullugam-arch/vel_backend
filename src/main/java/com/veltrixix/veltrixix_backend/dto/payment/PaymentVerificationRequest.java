package com.veltrixix.veltrixix_backend.dto.payment;

import jakarta.validation.constraints.NotBlank;

public class PaymentVerificationRequest {

    @NotBlank(message = "Verification status is required")
    private String verificationStatus;

    private String adminRemarks;

    public PaymentVerificationRequest() {
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getAdminRemarks() {
        return adminRemarks;
    }

    public void setAdminRemarks(String adminRemarks) {
        this.adminRemarks = adminRemarks;
    }
}