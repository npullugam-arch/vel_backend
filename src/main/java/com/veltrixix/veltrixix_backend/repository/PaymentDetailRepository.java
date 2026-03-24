package com.veltrixix.veltrixix_backend.repository;

import com.veltrixix.veltrixix_backend.entity.PaymentDetail;
import com.veltrixix.veltrixix_backend.entity.Registration;
import com.veltrixix.veltrixix_backend.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
    Optional<PaymentDetail> findByRegistration(Registration registration);
    List<PaymentDetail> findByVerificationStatus(VerificationStatus verificationStatus);
}