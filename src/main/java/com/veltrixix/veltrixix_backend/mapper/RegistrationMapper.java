package com.veltrixix.veltrixix_backend.mapper;

import com.veltrixix.veltrixix_backend.dto.registration.RegistrationResponse;
import com.veltrixix.veltrixix_backend.entity.Registration;

public final class RegistrationMapper {

    private RegistrationMapper() {
    }

    public static RegistrationResponse toResponse(Registration registration) {
        if (registration == null) {
            return null;
        }

        RegistrationResponse response = new RegistrationResponse();
        response.setId(registration.getId());
        response.setFullName(registration.getFullName());
        response.setCollegeName(registration.getCollegeName());
        response.setRollNumber(registration.getRollNumber());
        response.setCollegeEmail(registration.getCollegeEmail());
        response.setPersonalEmail(registration.getPersonalEmail());
        response.setWhatsappNumber(registration.getWhatsappNumber());
        response.setBranch(registration.getBranch());
        response.setYearOfStudy(registration.getYearOfStudy());
        response.setCity(registration.getCity());
        response.setInterestMessage(registration.getInterestMessage());
        response.setRegistrationType(registration.getRegistrationType() != null ? registration.getRegistrationType().name() : null);
        response.setReferenceId(registration.getReferenceId());
        response.setPaymentStatus(registration.getPaymentStatus() != null ? registration.getPaymentStatus().name() : null);
        response.setSelectionStatus(registration.getSelectionStatus() != null ? registration.getSelectionStatus().name() : null);
        response.setInternshipId(registration.getInternship() != null ? registration.getInternship().getId() : null);
        response.setEventId(registration.getEvent() != null ? registration.getEvent().getId() : null);
        response.setProjectId(registration.getProject() != null ? registration.getProject().getId() : null);
        response.setCreatedAt(registration.getCreatedAt());
        response.setUpdatedAt(registration.getUpdatedAt());
        return response;
    }
}