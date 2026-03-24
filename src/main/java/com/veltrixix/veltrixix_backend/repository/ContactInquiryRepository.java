package com.veltrixix.veltrixix_backend.repository;

import com.veltrixix.veltrixix_backend.entity.ContactInquiry;
import com.veltrixix.veltrixix_backend.enums.InquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInquiryRepository extends JpaRepository<ContactInquiry, Long> {
    List<ContactInquiry> findByStatus(InquiryStatus status);
    long countByStatus(InquiryStatus status);
}