package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.payment.PaymentDetailResponse;
import com.veltrixix.veltrixix_backend.dto.registration.PaymentSubmissionRequest;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationRequest;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationResponse;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationStatusUpdateRequest;

import java.util.List;

public interface RegistrationService {

    RegistrationResponse createRegistration(RegistrationRequest request);

    RegistrationResponse getRegistrationById(Long id);

    RegistrationResponse getRegistrationByReferenceId(String referenceId);

    List<RegistrationResponse> getAllRegistrations();

    void updateSelectionStatus(Long id, RegistrationStatusUpdateRequest request);

    PaymentDetailResponse submitPayment(Long registrationId, PaymentSubmissionRequest request);

    void deleteRegistration(Long id);
}