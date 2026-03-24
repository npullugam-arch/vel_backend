package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.payment.PaymentDetailResponse;
import com.veltrixix.veltrixix_backend.dto.registration.PaymentSubmissionRequest;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationRequest;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationResponse;
import com.veltrixix.veltrixix_backend.dto.registration.RegistrationStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.entity.Event;
import com.veltrixix.veltrixix_backend.entity.Internship;
import com.veltrixix.veltrixix_backend.entity.PaymentDetail;
import com.veltrixix.veltrixix_backend.entity.Project;
import com.veltrixix.veltrixix_backend.entity.Registration;
import com.veltrixix.veltrixix_backend.enums.PaymentStatus;
import com.veltrixix.veltrixix_backend.enums.RegistrationType;
import com.veltrixix.veltrixix_backend.enums.SelectionStatus;
import com.veltrixix.veltrixix_backend.enums.VerificationStatus;
import com.veltrixix.veltrixix_backend.exception.BadRequestException;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.mapper.PaymentDetailMapper;
import com.veltrixix.veltrixix_backend.mapper.RegistrationMapper;
import com.veltrixix.veltrixix_backend.repository.EventRepository;
import com.veltrixix.veltrixix_backend.repository.InternshipRepository;
import com.veltrixix.veltrixix_backend.repository.PaymentDetailRepository;
import com.veltrixix.veltrixix_backend.repository.ProjectRepository;
import com.veltrixix.veltrixix_backend.repository.RegistrationRepository;
import com.veltrixix.veltrixix_backend.service.RegistrationService;
import com.veltrixix.veltrixix_backend.util.ReferenceIdGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final InternshipRepository internshipRepository;
    private final EventRepository eventRepository;
    private final ProjectRepository projectRepository;
    private final PaymentDetailRepository paymentDetailRepository;

    public RegistrationServiceImpl(
            RegistrationRepository registrationRepository,
            InternshipRepository internshipRepository,
            EventRepository eventRepository,
            ProjectRepository projectRepository,
            PaymentDetailRepository paymentDetailRepository
    ) {
        this.registrationRepository = registrationRepository;
        this.internshipRepository = internshipRepository;
        this.eventRepository = eventRepository;
        this.projectRepository = projectRepository;
        this.paymentDetailRepository = paymentDetailRepository;
    }

    @Override
    public RegistrationResponse createRegistration(RegistrationRequest request) {
        RegistrationType registrationType = parseRegistrationType(request.getRegistrationType());

        Registration registration = new Registration();
        registration.setFullName(request.getFullName());
        registration.setCollegeName(request.getCollegeName());
        registration.setRollNumber(request.getRollNumber());
        registration.setCollegeEmail(request.getCollegeEmail());
        registration.setPersonalEmail(request.getPersonalEmail());
        registration.setWhatsappNumber(request.getWhatsappNumber());
        registration.setBranch(request.getBranch());
        registration.setYearOfStudy(request.getYearOfStudy());
        registration.setCity(request.getCity());
        registration.setInterestMessage(request.getInterestMessage());
        registration.setRegistrationType(registrationType);
        registration.setReferenceId(ReferenceIdGenerator.generate("VEL"));
        registration.setPaymentStatus(PaymentStatus.PENDING);
        registration.setSelectionStatus(SelectionStatus.NEW);

        attachTargetEntity(request, registrationType, registration);

        return RegistrationMapper.toResponse(registrationRepository.save(registration));
    }

    @Override
    public RegistrationResponse getRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        return RegistrationMapper.toResponse(registration);
    }

    @Override
    public RegistrationResponse getRegistrationByReferenceId(String referenceId) {
        Registration registration = registrationRepository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        return RegistrationMapper.toResponse(registration);
    }

    @Override
    public List<RegistrationResponse> getAllRegistrations() {
        return registrationRepository.findAll()
                .stream()
                .map(RegistrationMapper::toResponse)
                .toList();
    }

    @Override
    public void updateSelectionStatus(Long id, RegistrationStatusUpdateRequest request) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));

        registration.setSelectionStatus(parseSelectionStatus(request.getSelectionStatus()));
        registrationRepository.save(registration);
    }

    @Override
    public PaymentDetailResponse submitPayment(Long registrationId, PaymentSubmissionRequest request) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));

        PaymentDetail paymentDetail = paymentDetailRepository.findByRegistration(registration)
                .orElseGet(PaymentDetail::new);

        paymentDetail.setRegistration(registration);
        paymentDetail.setTransactionId(request.getTransactionId());
        paymentDetail.setScreenshotUrl(request.getScreenshotUrl());
        paymentDetail.setAmount(request.getAmount());
        paymentDetail.setPaymentDate(request.getPaymentDate());
        paymentDetail.setVerificationStatus(VerificationStatus.PENDING);

        PaymentDetail savedPayment = paymentDetailRepository.save(paymentDetail);

        registration.setPaymentStatus(PaymentStatus.SUBMITTED);
        registrationRepository.save(registration);

        return PaymentDetailMapper.toResponse(savedPayment);
    }

    @Override
    public void deleteRegistration(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        registrationRepository.delete(registration);
    }

    private void attachTargetEntity(
            RegistrationRequest request,
            RegistrationType registrationType,
            Registration registration
    ) {
        if (registrationType == RegistrationType.INTERNSHIP) {
            if (request.getInternshipId() == null) {
                throw new BadRequestException("Internship id is required");
            }

            Internship internship = internshipRepository.findById(request.getInternshipId())
                    .orElseThrow(() -> new ResourceNotFoundException("Internship not found"));
            registration.setInternship(internship);
        } else if (registrationType == RegistrationType.EVENT) {
            if (request.getEventId() == null) {
                throw new BadRequestException("Event id is required");
            }

            Event event = eventRepository.findById(request.getEventId())
                    .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
            registration.setEvent(event);
        } else if (registrationType == RegistrationType.PROJECT) {
            if (request.getProjectId() == null) {
                throw new BadRequestException("Project id is required");
            }

            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
            registration.setProject(project);
        }
    }

    private RegistrationType parseRegistrationType(String registrationType) {
        try {
            return RegistrationType.valueOf(registrationType.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid registration type value");
        }
    }

    private SelectionStatus parseSelectionStatus(String selectionStatus) {
        try {
            return SelectionStatus.valueOf(selectionStatus.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid selection status value");
        }
    }
}