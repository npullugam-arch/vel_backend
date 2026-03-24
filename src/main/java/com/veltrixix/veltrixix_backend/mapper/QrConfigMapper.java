package com.veltrixix.veltrixix_backend.mapper;

import com.veltrixix.veltrixix_backend.dto.qr.QrConfigResponse;
import com.veltrixix.veltrixix_backend.entity.QrConfig;

public final class QrConfigMapper {

    private QrConfigMapper() {
    }

    public static QrConfigResponse toResponse(QrConfig qrConfig) {
        if (qrConfig == null) {
            return null;
        }

        QrConfigResponse response = new QrConfigResponse();
        response.setId(qrConfig.getId());
        response.setTitle(qrConfig.getTitle());
        response.setQrImageUrl(qrConfig.getQrImageUrl());
        response.setUpiId(qrConfig.getUpiId());
        response.setInstructions(qrConfig.getInstructions());
        response.setActive(qrConfig.getActive());
        response.setCreatedAt(qrConfig.getCreatedAt());
        response.setUpdatedAt(qrConfig.getUpdatedAt());
        return response;
    }
}