package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.payment.PaymentDetailResponse;
import com.veltrixix.veltrixix_backend.dto.payment.PaymentVerificationRequest;
import com.veltrixix.veltrixix_backend.entity.PaymentDetail;
import com.veltrixix.veltrixix_backend.entity.Registration;
import com.veltrixix.veltrixix_backend.enums.PaymentStatus;
import com.veltrixix.veltrixix_backend.enums.VerificationStatus;
import com.veltrixix.veltrixix_backend.exception.BadRequestException;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.mapper.PaymentDetailMapper;
import com.veltrixix.veltrixix_backend.repository.PaymentDetailRepository;
import com.veltrixix.veltrixix_backend.repository.RegistrationRepository;
import com.veltrixix.veltrixix_backend.service.PaymentDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {

    private final PaymentDetailRepository paymentDetailRepository;
    private final RegistrationRepository registrationRepository;

    public PaymentDetailServiceImpl(
            PaymentDetailRepository paymentDetailRepository,
            RegistrationRepository registrationRepository
    ) {
        this.paymentDetailRepository = paymentDetailRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public PaymentDetailResponse getPaymentById(Long id) {
        PaymentDetail paymentDetail = paymentDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return PaymentDetailMapper.toResponse(paymentDetail);
    }

    @Override
    public List<PaymentDetailResponse> getAllPayments() {
        return paymentDetailRepository.findAll()
                .stream()
                .map(PaymentDetailMapper::toResponse)
                .toList();
    }

    @Override
    public List<PaymentDetailResponse> getPaymentsByVerificationStatus(String verificationStatus) {
        VerificationStatus status = parseVerificationStatus(verificationStatus);
        return paymentDetailRepository.findByVerificationStatus(status)
                .stream()
                .map(PaymentDetailMapper::toResponse)
                .toList();
    }

    @Override
    public void verifyPayment(Long id, PaymentVerificationRequest request) {
        PaymentDetail paymentDetail = paymentDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        VerificationStatus verificationStatus = parseVerificationStatus(request.getVerificationStatus());
        paymentDetail.setVerificationStatus(verificationStatus);
        paymentDetail.setAdminRemarks(request.getAdminRemarks());
        paymentDetailRepository.save(paymentDetail);

        Registration registration = paymentDetail.getRegistration();
        if (registration != null) {
            if (verificationStatus == VerificationStatus.VERIFIED) {
                registration.setPaymentStatus(PaymentStatus.VERIFIED);
            } else if (verificationStatus == VerificationStatus.REJECTED) {
                registration.setPaymentStatus(PaymentStatus.REJECTED);
            } else {
                registration.setPaymentStatus(PaymentStatus.SUBMITTED);
            }
            registrationRepository.save(registration);
        }
    }

    private VerificationStatus parseVerificationStatus(String verificationStatus) {
        try {
            return VerificationStatus.valueOf(verificationStatus.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid verification status value");
        }
    }
}