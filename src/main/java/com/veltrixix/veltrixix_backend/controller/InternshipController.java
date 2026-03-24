package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.dto.internship.InternshipRequest;
import com.veltrixix.veltrixix_backend.dto.internship.InternshipResponse;
import com.veltrixix.veltrixix_backend.dto.internship.InternshipStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.service.InternshipService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @PostMapping
    public ApiResponse<InternshipResponse> createInternship(@Valid @RequestBody InternshipRequest request) {
        return ApiResponse.success("Internship created successfully", internshipService.createInternship(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<InternshipResponse> updateInternship(
            @PathVariable Long id,
            @Valid @RequestBody InternshipRequest request
    ) {
        return ApiResponse.success("Internship updated successfully", internshipService.updateInternship(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<InternshipResponse> getInternshipById(@PathVariable Long id) {
        return ApiResponse.success(internshipService.getInternshipById(id));
    }

    @GetMapping
    public ApiResponse<List<InternshipResponse>> getAllInternships() {
        return ApiResponse.success(internshipService.getAllInternships());
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<InternshipResponse>> getInternshipsByStatus(@PathVariable String status) {
        return ApiResponse.success(internshipService.getInternshipsByStatus(status));
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<MessageResponse> updateInternshipStatus(
            @PathVariable Long id,
            @Valid @RequestBody InternshipStatusUpdateRequest request
    ) {
        internshipService.updateInternshipStatus(id, request);
        return ApiResponse.success("Internship status updated successfully", MessageResponse.of("Internship status updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MessageResponse> deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return ApiResponse.success("Internship deleted successfully", MessageResponse.of("Internship deleted successfully"));
    }
}