package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.payment.PaymentDetailResponse;
import com.veltrixix.veltrixix_backend.dto.payment.PaymentVerificationRequest;

import java.util.List;

public interface PaymentDetailService {

    PaymentDetailResponse getPaymentById(Long id);

    List<PaymentDetailResponse> getAllPayments();

    List<PaymentDetailResponse> getPaymentsByVerificationStatus(String verificationStatus);

    void verifyPayment(Long id, PaymentVerificationRequest request);
}