package com.veltrixix.veltrixix_backend.repository;

import com.veltrixix.veltrixix_backend.entity.Registration;
import com.veltrixix.veltrixix_backend.enums.PaymentStatus;
import com.veltrixix.veltrixix_backend.enums.SelectionStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findByReferenceId(String referenceId);

    long countBySelectionStatus(SelectionStatus selectionStatus);

    long countByPaymentStatus(PaymentStatus paymentStatus);

    boolean existsByEventId(Long eventId);

    @Modifying
    @Transactional
    @Query("delete from Registration r where r.event.id = :eventId")
    void deleteRegistrationsByEventId(@Param("eventId") Long eventId);
}