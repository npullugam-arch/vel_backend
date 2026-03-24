package com.veltrixix.veltrixix_backend.dto.contact;

import jakarta.validation.constraints.NotBlank;

public class ContactStatusUpdateRequest {

    @NotBlank(message = "Status is required")
    private String status;

    public ContactStatusUpdateRequest() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}