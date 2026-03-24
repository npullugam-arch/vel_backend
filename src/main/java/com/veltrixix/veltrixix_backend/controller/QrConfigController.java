package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.dto.qr.QrConfigRequest;
import com.veltrixix.veltrixix_backend.dto.qr.QrConfigResponse;
import com.veltrixix.veltrixix_backend.service.QrConfigService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qr-configs")
public class QrConfigController {

    private final QrConfigService qrConfigService;

    public QrConfigController(QrConfigService qrConfigService) {
        this.qrConfigService = qrConfigService;
    }

    @PostMapping
    public ApiResponse<QrConfigResponse> createQrConfig(@Valid @RequestBody QrConfigRequest request) {
        return ApiResponse.success("QR config created successfully", qrConfigService.createQrConfig(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<QrConfigResponse> updateQrConfig(
            @PathVariable Long id,
            @Valid @RequestBody QrConfigRequest request
    ) {
        return ApiResponse.success("QR config updated successfully", qrConfigService.updateQrConfig(id, request));
    }

    @GetMapping
    public ApiResponse<List<QrConfigResponse>> getAllQrConfigs() {
        return ApiResponse.success(qrConfigService.getAllQrConfigs());
    }

    @GetMapping("/{id}")
    public ApiResponse<QrConfigResponse> getQrConfigById(@PathVariable Long id) {
        return ApiResponse.success(qrConfigService.getQrConfigById(id));
    }

    @GetMapping("/active/current")
    public ApiResponse<QrConfigResponse> getActiveQrConfig() {
        return ApiResponse.success(qrConfigService.getActiveQrConfig());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MessageResponse> deleteQrConfig(@PathVariable Long id) {
        qrConfigService.deleteQrConfig(id);
        return ApiResponse.success("QR config deleted successfully", MessageResponse.of("QR config deleted successfully"));
    }
}