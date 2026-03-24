package com.veltrixix.veltrixix_backend.dto.registration;

import jakarta.validation.constraints.NotBlank;

public class RegistrationStatusUpdateRequest {

    @NotBlank(message = "Selection status is required")
    private String selectionStatus;

    public RegistrationStatusUpdateRequest() {
    }

    public String getSelectionStatus() {
        return selectionStatus;
    }

    public void setSelectionStatus(String selectionStatus) {
        this.selectionStatus = selectionStatus;
    }
}