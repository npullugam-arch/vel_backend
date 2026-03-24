package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.dto.payment.PaymentDetailResponse;
import com.veltrixix.veltrixix_backend.dto.registration.PaymentSubmissionRequest;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationRequest;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationResponse;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ApiResponse<RegistrationResponse> createRegistration(@Valid @RequestBody RegistrationRequest request) {
        return ApiResponse.success("Registration submitted successfully", registrationService.createRegistration(request));
    }

    @GetMapping
    public ApiResponse<List<RegistrationResponse>> getAllRegistrations() {
        return ApiResponse.success(registrationService.getAllRegistrations());
    }

    @GetMapping("/{id}")
    public ApiResponse<RegistrationResponse> getRegistrationById(@PathVariable Long id) {
        return ApiResponse.success(registrationService.getRegistrationById(id));
    }

    @GetMapping("/reference/{referenceId}")
    public ApiResponse<RegistrationResponse> getRegistrationByReferenceId(@PathVariable String referenceId) {
        return ApiResponse.success(registrationService.getRegistrationByReferenceId(referenceId));
    }

    @PatchMapping("/{id}/selection-status")
    public ApiResponse<MessageResponse> updateSelectionStatus(
            @PathVariable Long id,
            @Valid @RequestBody RegistrationStatusUpdateRequest request
    ) {
        registrationService.updateSelectionStatus(id, request);
        return ApiResponse.success("Selection status updated successfully", MessageResponse.of("Selection status updated successfully"));
    }

    @PostMapping("/{registrationId}/payment")
    public ApiResponse<PaymentDetailResponse> submitPayment(
            @PathVariable Long registrationId,
            @RequestBody PaymentSubmissionRequest request
    ) {
        return ApiResponse.success("Payment submitted successfully", registrationService.submitPayment(registrationId, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MessageResponse> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ApiResponse.success("Registration deleted successfully", MessageResponse.of("Registration deleted successfully"));
    }
}