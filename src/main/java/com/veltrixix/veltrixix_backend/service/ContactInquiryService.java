package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.contact.ContactInquiryRequest;
import com.veltrixix.veltrixix_backend.dto.contact.ContactInquiryResponse;
import com.veltrixix.veltrixix_backend.dto.contact.ContactStatusUpdateRequest;

import java.util.List;

public interface ContactInquiryService {

    ContactInquiryResponse createInquiry(ContactInquiryRequest request);

    List<ContactInquiryResponse> getAllInquiries();

    ContactInquiryResponse getInquiryById(Long id);

    void updateInquiryStatus(Long id, ContactStatusUpdateRequest request);

    void deleteInquiry(Long id);
}