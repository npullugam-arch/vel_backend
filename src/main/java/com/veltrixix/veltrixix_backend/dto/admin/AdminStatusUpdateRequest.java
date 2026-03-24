package com.veltrixix.veltrixix_backend.dto.admin;

import jakarta.validation.constraints.NotNull;

public class AdminStatusUpdateRequest {

    @NotNull(message = "Active status is required")
    private Boolean active;

    public AdminStatusUpdateRequest() {
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}