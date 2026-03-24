package com.veltrixix.veltrixix_backend.dto.qr;

import jakarta.validation.constraints.NotBlank;

public class QrConfigRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String qrImageUrl;
    private String upiId;
    private String instructions;
    private Boolean active;

    public QrConfigRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQrImageUrl() {
        return qrImageUrl;
    }

    public void setQrImageUrl(String qrImageUrl) {
        this.qrImageUrl = qrImageUrl;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}