package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.qr.QrConfigRequest;
import com.veltrixix.veltrixix_backend.dto.qr.QrConfigResponse;

import java.util.List;

public interface QrConfigService {

    QrConfigResponse createQrConfig(QrConfigRequest request);

    QrConfigResponse updateQrConfig(Long id, QrConfigRequest request);

    QrConfigResponse getQrConfigById(Long id);

    QrConfigResponse getActiveQrConfig();

    List<QrConfigResponse> getAllQrConfigs();

    void deleteQrConfig(Long id);
}