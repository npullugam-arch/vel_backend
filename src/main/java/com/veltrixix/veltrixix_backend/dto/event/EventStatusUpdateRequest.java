package com.veltrixix.veltrixix_backend.dto.event;

import jakarta.validation.constraints.NotBlank;

public class EventStatusUpdateRequest {

    @NotBlank(message = "Status is required")
    private String status;

    public EventStatusUpdateRequest() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}