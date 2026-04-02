package com.veltrixix.veltrixix_backend.repository;

import com.veltrixix.veltrixix_backend.entity.PaymentDetail;
import com.veltrixix.veltrixix_backend.entity.Registration;
import com.veltrixix.veltrixix_backend.enums.VerificationStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
    Optional<PaymentDetail> findByRegistration(Registration registration);
    List<PaymentDetail> findByVerificationStatus(VerificationStatus verificationStatus);
    boolean existsByRegistrationEventId(Long eventId);

    @Modifying
    @Transactional
    @Query("delete from PaymentDetail p where p.registration.event.id = :eventId")
    void deleteByRegistrationEventId(@Param("eventId") Long eventId);
}
