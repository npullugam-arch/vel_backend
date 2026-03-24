package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.internship.InternshipRequest;
import com.veltrixix.veltrixix_backend.dto.internship.InternshipResponse;
import com.veltrixix.veltrixix_backend.dto.internship.InternshipStatusUpdateRequest;

import java.util.List;

public interface InternshipService {

    InternshipResponse createInternship(InternshipRequest request);

    InternshipResponse updateInternship(Long id, InternshipRequest request);

    InternshipResponse getInternshipById(Long id);

    List<InternshipResponse> getAllInternships();

    List<InternshipResponse> getInternshipsByStatus(String status);

    void updateInternshipStatus(Long id, InternshipStatusUpdateRequest request);

    void deleteInternship(Long id);
}