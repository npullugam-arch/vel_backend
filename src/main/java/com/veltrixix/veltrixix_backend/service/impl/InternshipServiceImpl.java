package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.internship.InternshipRequest;
import com.veltrixix.veltrixix_backend.dto.internship.InternshipResponse;
import com.veltrixix.veltrixix_backend.dto.internship.InternshipStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.entity.Internship;
import com.veltrixix.veltrixix_backend.enums.InternshipStatus;
import com.veltrixix.veltrixix_backend.enums.ModeType;
import com.veltrixix.veltrixix_backend.exception.BadRequestException;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.mapper.InternshipMapper;
import com.veltrixix.veltrixix_backend.repository.InternshipRepository;
import com.veltrixix.veltrixix_backend.service.InternshipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;

    public InternshipServiceImpl(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    @Override
    public InternshipResponse createInternship(InternshipRequest request) {
        Internship internship = new Internship();
        mapRequestToEntity(request, internship);
        return InternshipMapper.toResponse(internshipRepository.save(internship));
    }

    @Override
    public InternshipResponse updateInternship(Long id, InternshipRequest request) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found"));

        mapRequestToEntity(request, internship);
        return InternshipMapper.toResponse(internshipRepository.save(internship));
    }

    @Override
    public InternshipResponse getInternshipById(Long id) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found"));
        return InternshipMapper.toResponse(internship);
    }

    @Override
    public List<InternshipResponse> getAllInternships() {
        return internshipRepository.findAll()
                .stream()
                .map(InternshipMapper::toResponse)
                .toList();
    }

    @Override
    public List<InternshipResponse> getInternshipsByStatus(String status) {
        InternshipStatus internshipStatus = parseInternshipStatus(status);
        return internshipRepository.findByStatus(internshipStatus)
                .stream()
                .map(InternshipMapper::toResponse)
                .toList();
    }

    @Override
    public void updateInternshipStatus(Long id, InternshipStatusUpdateRequest request) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found"));

        internship.setStatus(parseInternshipStatus(request.getStatus()));
        internshipRepository.save(internship);
    }

    @Override
    public void deleteInternship(Long id) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found"));
        internshipRepository.delete(internship);
    }

    private void mapRequestToEntity(InternshipRequest request, Internship internship) {
        internship.setTitle(request.getTitle());
        internship.setDomain(request.getDomain());
        internship.setMentorName(request.getMentorName());
        internship.setDescription(request.getDescription());
        internship.setAboutText(request.getAboutText());
        internship.setScheduleText(request.getScheduleText());
        internship.setDuration(request.getDuration());
        internship.setMode(parseModeType(request.getMode()));
        internship.setFee(request.getFee());
        internship.setCapacity(request.getCapacity());
        internship.setStatus(parseInternshipStatus(request.getStatus()));
        internship.setRegistrationOpen(
                request.getRegistrationOpen() != null ? request.getRegistrationOpen() : true
        );
        internship.setStartDate(request.getStartDate());
        internship.setEndDate(request.getEndDate());
    }

    private InternshipStatus parseInternshipStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new BadRequestException("Internship status must not be empty");
        }

        String normalizedStatus = status.trim()
                .toUpperCase()
                .replace(" ", "_")
                .replace("-", "_");

        try {
            return InternshipStatus.valueOf(normalizedStatus);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(
                    "Invalid internship status value: " + status
            );
        }
    }

    private ModeType parseModeType(String mode) {
        if (mode == null || mode.trim().isEmpty()) {
            return null;
        }

        String normalizedMode = mode.trim()
                .toUpperCase()
                .replace(" ", "_")
                .replace("-", "_");

        try {
            return ModeType.valueOf(normalizedMode);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid mode value: " + mode);
        }
    }
}