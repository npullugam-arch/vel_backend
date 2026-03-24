package com.veltrixix.veltrixix_backend.mapper;

import com.veltrixix.veltrixix_backend.dto.payment.PaymentDetailResponse;
import com.veltrixix.veltrixix_backend.entity.PaymentDetail;

public final class PaymentDetailMapper {

    private PaymentDetailMapper() {
    }

    public static PaymentDetailResponse toResponse(PaymentDetail paymentDetail) {
        if (paymentDetail == null) {
            return null;
        }

        PaymentDetailResponse response = new PaymentDetailResponse();
        response.setId(paymentDetail.getId());
        response.setRegistrationId(
                paymentDetail.getRegistration() != null ? paymentDetail.getRegistration().getId() : null
        );
        response.setQrImageUrl(paymentDetail.getQrImageUrl());
        response.setTransactionId(paymentDetail.getTransactionId());
        response.setScreenshotUrl(paymentDetail.getScreenshotUrl());
        response.setAmount(paymentDetail.getAmount());
        response.setPaymentDate(paymentDetail.getPaymentDate());
        response.setVerificationStatus(
                paymentDetail.getVerificationStatus() != null ? paymentDetail.getVerificationStatus().name() : null
        );
        response.setAdminRemarks(paymentDetail.getAdminRemarks());
        response.setCreatedAt(paymentDetail.getCreatedAt());
        response.setUpdatedAt(paymentDetail.getUpdatedAt());
        return response;
    }
}