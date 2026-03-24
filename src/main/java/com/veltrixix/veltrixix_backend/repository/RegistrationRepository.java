package com.veltrixix.veltrixix_backend.repository;

import com.veltrixix.veltrixix_backend.entity.Registration;
import com.veltrixix.veltrixix_backend.enums.PaymentStatus;
import com.veltrixix.veltrixix_backend.enums.SelectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByReferenceId(String referenceId);
    long countBySelectionStatus(SelectionStatus selectionStatus);
    long countByPaymentStatus(PaymentStatus paymentStatus);
}