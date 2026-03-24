package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.qr.QrConfigRequest;
import com.veltrixix.veltrixix_backend.dto.qr.QrConfigResponse;
import com.veltrixix.veltrixix_backend.entity.QrConfig;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.mapper.QrConfigMapper;
import com.veltrixix.veltrixix_backend.repository.QrConfigRepository;
import com.veltrixix.veltrixix_backend.service.QrConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QrConfigServiceImpl implements QrConfigService {

    private final QrConfigRepository qrConfigRepository;

    public QrConfigServiceImpl(QrConfigRepository qrConfigRepository) {
        this.qrConfigRepository = qrConfigRepository;
    }

    @Override
    public QrConfigResponse createQrConfig(QrConfigRequest request) {
        if (Boolean.TRUE.equals(request.getActive())) {
            deactivateExistingActiveQr();
        }

        QrConfig qrConfig = new QrConfig();
        mapRequestToEntity(request, qrConfig);
        return QrConfigMapper.toResponse(qrConfigRepository.save(qrConfig));
    }

    @Override
    public QrConfigResponse updateQrConfig(Long id, QrConfigRequest request) {
        QrConfig qrConfig = qrConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QR config not found"));

        if (Boolean.TRUE.equals(request.getActive())) {
            deactivateExistingActiveQr();
        }

        mapRequestToEntity(request, qrConfig);
        return QrConfigMapper.toResponse(qrConfigRepository.save(qrConfig));
    }

    @Override
    public QrConfigResponse getQrConfigById(Long id) {
        QrConfig qrConfig = qrConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QR config not found"));
        return QrConfigMapper.toResponse(qrConfig);
    }

    @Override
    public QrConfigResponse getActiveQrConfig() {
        QrConfig qrConfig = qrConfigRepository.findByActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("Active QR config not found"));
        return QrConfigMapper.toResponse(qrConfig);
    }

    @Override
    public List<QrConfigResponse> getAllQrConfigs() {
        return qrConfigRepository.findAll()
                .stream()
                .map(QrConfigMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteQrConfig(Long id) {
        QrConfig qrConfig = qrConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QR config not found"));
        qrConfigRepository.delete(qrConfig);
    }

    private void mapRequestToEntity(QrConfigRequest request, QrConfig qrConfig) {
        qrConfig.setTitle(request.getTitle());
        qrConfig.setQrImageUrl(request.getQrImageUrl());
        qrConfig.setUpiId(request.getUpiId());
        qrConfig.setInstructions(request.getInstructions());
        qrConfig.setActive(request.getActive() != null ? request.getActive() : true);
    }

    private void deactivateExistingActiveQr() {
        qrConfigRepository.findByActiveTrue().ifPresent(existing -> {
            existing.setActive(false);
            qrConfigRepository.save(existing);
        });
    }
}