package com.veltrixix.veltrixix_backend.mapper;

import com.veltrixix.veltrixix_backend.dto.contact.ContactInquiryResponse;
import com.veltrixix.veltrixix_backend.entity.ContactInquiry;

public final class ContactInquiryMapper {

    private ContactInquiryMapper() {
    }

    public static ContactInquiryResponse toResponse(ContactInquiry inquiry) {
        if (inquiry == null) {
            return null;
        }

        ContactInquiryResponse response = new ContactInquiryResponse();
        response.setId(inquiry.getId());
        response.setName(inquiry.getName());
        response.setEmail(inquiry.getEmail());
        response.setPhone(inquiry.getPhone());
        response.setSubject(inquiry.getSubject());
        response.setMessage(inquiry.getMessage());
        response.setStatus(inquiry.getStatus() != null ? inquiry.getStatus().name() : null);
        response.setCreatedAt(inquiry.getCreatedAt());
        response.setUpdatedAt(inquiry.getUpdatedAt());
        return response;
    }
}