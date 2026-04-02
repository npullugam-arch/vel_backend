package com.veltrixix.veltrixix_backend.mapper;

import com.veltrixix.veltrixix_backend.dto.internship.InternshipResponse;
import com.veltrixix.veltrixix_backend.entity.Internship;

public final class InternshipMapper {

    private InternshipMapper() {
    }

    public static InternshipResponse toResponse(Internship internship) {
        if (internship == null) {
            return null;
        }

        InternshipResponse response = new InternshipResponse();
        response.setId(internship.getId());
        response.setTitle(internship.getTitle());
        response.setDomain(internship.getDomain());
        response.setMentorName(internship.getMentorName());
        response.setDescription(internship.getDescription());
        response.setAboutText(internship.getAboutText());
        response.setScheduleText(internship.getScheduleText());
        response.setDuration(internship.getDuration());
        response.setMode(internship.getMode() != null ? internship.getMode().toString() : null);
        response.setFee(internship.getFee());
        response.setCapacity(internship.getCapacity());
        response.setStatus(internship.getStatus() != null ? internship.getStatus().toString() : null);
        response.setRegistrationOpen(internship.getRegistrationOpen());
        response.setStartDate(internship.getStartDate());
        response.setEndDate(internship.getEndDate());
        response.setCreatedAt(internship.getCreatedAt());
        response.setUpdatedAt(internship.getUpdatedAt());
        return response;
    }
}