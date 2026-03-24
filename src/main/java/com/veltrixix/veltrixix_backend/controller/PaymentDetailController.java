package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.dto.payment.PaymentDetailResponse;
import com.veltrixix.veltrixix_backend.dto.payment.PaymentVerificationRequest;
import com.veltrixix.veltrixix_backend.service.PaymentDetailService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentDetailController {

    private final PaymentDetailService paymentDetailService;

    public PaymentDetailController(PaymentDetailService paymentDetailService) {
        this.paymentDetailService = paymentDetailService;
    }

    @GetMapping
    public ApiResponse<List<PaymentDetailResponse>> getAllPayments() {
        return ApiResponse.success(paymentDetailService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ApiResponse<PaymentDetailResponse> getPaymentById(@PathVariable Long id) {
        return ApiResponse.success(paymentDetailService.getPaymentById(id));
    }

    @GetMapping("/verification-status/{verificationStatus}")
    public ApiResponse<List<PaymentDetailResponse>> getPaymentsByVerificationStatus(@PathVariable String verificationStatus) {
        return ApiResponse.success(paymentDetailService.getPaymentsByVerificationStatus(verificationStatus));
    }

    @PatchMapping("/{id}/verify")
    public ApiResponse<MessageResponse> verifyPayment(
            @PathVariable Long id,
            @Valid @RequestBody PaymentVerificationRequest request
    ) {
        paymentDetailService.verifyPayment(id, request);
        return ApiResponse.success("Payment verification updated successfully", MessageResponse.of("Payment verification updated successfully"));
    }
}