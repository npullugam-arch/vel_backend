package com.veltrixix.veltrixix_backend.dto.project;

import jakarta.validation.constraints.NotBlank;

public class ProjectStatusUpdateRequest {

    @NotBlank(message = "Status text is required")
    private String statusText;

    public ProjectStatusUpdateRequest() {
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}