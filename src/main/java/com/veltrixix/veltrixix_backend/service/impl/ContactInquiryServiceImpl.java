package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.contact.ContactInquiryRequest;
import com.veltrixix.veltrixix_backend.dto.contact.ContactInquiryResponse;
import com.veltrixix.veltrixix_backend.dto.contact.ContactStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.entity.ContactInquiry;
import com.veltrixix.veltrixix_backend.enums.InquiryStatus;
import com.veltrixix.veltrixix_backend.exception.BadRequestException;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.mapper.ContactInquiryMapper;
import com.veltrixix.veltrixix_backend.repository.ContactInquiryRepository;
import com.veltrixix.veltrixix_backend.service.ContactInquiryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInquiryServiceImpl implements ContactInquiryService {

    private final ContactInquiryRepository contactInquiryRepository;

    public ContactInquiryServiceImpl(ContactInquiryRepository contactInquiryRepository) {
        this.contactInquiryRepository = contactInquiryRepository;
    }

    @Override
    public ContactInquiryResponse createInquiry(ContactInquiryRequest request) {
        ContactInquiry inquiry = new ContactInquiry();
        inquiry.setName(request.getName());
        inquiry.setEmail(request.getEmail());
        inquiry.setPhone(request.getPhone());
        inquiry.setSubject(request.getSubject());
        inquiry.setMessage(request.getMessage());
        inquiry.setStatus(InquiryStatus.NEW);

        return ContactInquiryMapper.toResponse(contactInquiryRepository.save(inquiry));
    }

    @Override
    public List<ContactInquiryResponse> getAllInquiries() {
        return contactInquiryRepository.findAll()
                .stream()
                .map(ContactInquiryMapper::toResponse)
                .toList();
    }

    @Override
    public ContactInquiryResponse getInquiryById(Long id) {
        ContactInquiry inquiry = contactInquiryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact inquiry not found"));
        return ContactInquiryMapper.toResponse(inquiry);
    }

    @Override
    public void updateInquiryStatus(Long id, ContactStatusUpdateRequest request) {
        ContactInquiry inquiry = contactInquiryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact inquiry not found"));

        inquiry.setStatus(parseInquiryStatus(request.getStatus()));
        contactInquiryRepository.save(inquiry);
    }

    @Override
    public void deleteInquiry(Long id) {
        ContactInquiry inquiry = contactInquiryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact inquiry not found"));
        contactInquiryRepository.delete(inquiry);
    }

    private InquiryStatus parseInquiryStatus(String status) {
        try {
            return InquiryStatus.valueOf(status.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid inquiry status value");
        }
    }
}