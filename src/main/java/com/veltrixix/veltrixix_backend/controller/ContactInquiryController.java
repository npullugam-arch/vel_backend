package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.dto.contact.ContactInquiryRequest;
import com.veltrixix.veltrixix_backend.dto.contact.ContactInquiryResponse;
import com.veltrixix.veltrixix_backend.dto.contact.ContactStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.service.ContactInquiryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-inquiries")
public class ContactInquiryController {

    private final ContactInquiryService contactInquiryService;

    public ContactInquiryController(ContactInquiryService contactInquiryService) {
        this.contactInquiryService = contactInquiryService;
    }

    @PostMapping
    public ApiResponse<ContactInquiryResponse> createInquiry(@Valid @RequestBody ContactInquiryRequest request) {
        return ApiResponse.success("Inquiry submitted successfully", contactInquiryService.createInquiry(request));
    }

    @GetMapping
    public ApiResponse<List<ContactInquiryResponse>> getAllInquiries() {
        return ApiResponse.success(contactInquiryService.getAllInquiries());
    }

    @GetMapping("/{id}")
    public ApiResponse<ContactInquiryResponse> getInquiryById(@PathVariable Long id) {
        return ApiResponse.success(contactInquiryService.getInquiryById(id));
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<MessageResponse> updateInquiryStatus(
            @PathVariable Long id,
            @Valid @RequestBody ContactStatusUpdateRequest request
    ) {
        contactInquiryService.updateInquiryStatus(id, request);
        return ApiResponse.success("Inquiry status updated successfully", MessageResponse.of("Inquiry status updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MessageResponse> deleteInquiry(@PathVariable Long id) {
        contactInquiryService.deleteInquiry(id);
        return ApiResponse.success("Inquiry deleted successfully", MessageResponse.of("Inquiry deleted successfully"));
    }
}