package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.dashboard.DashboardSummaryResponse;
import com.veltrixix.veltrixix_backend.enums.InquiryStatus;
import com.veltrixix.veltrixix_backend.enums.PaymentStatus;
import com.veltrixix.veltrixix_backend.enums.SelectionStatus;
import com.veltrixix.veltrixix_backend.repository.ContactInquiryRepository;
import com.veltrixix.veltrixix_backend.repository.EventRepository;
import com.veltrixix.veltrixix_backend.repository.InternshipRepository;
import com.veltrixix.veltrixix_backend.repository.PaymentDetailRepository;
import com.veltrixix.veltrixix_backend.repository.ProjectRepository;
import com.veltrixix.veltrixix_backend.repository.RegistrationRepository;
import com.veltrixix.veltrixix_backend.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final InternshipRepository internshipRepository;
    private final EventRepository eventRepository;
    private final ProjectRepository projectRepository;
    private final RegistrationRepository registrationRepository;
    private final PaymentDetailRepository paymentDetailRepository;
    private final ContactInquiryRepository contactInquiryRepository;

    public DashboardServiceImpl(
            InternshipRepository internshipRepository,
            EventRepository eventRepository,
            ProjectRepository projectRepository,
            RegistrationRepository registrationRepository,
            PaymentDetailRepository paymentDetailRepository,
            ContactInquiryRepository contactInquiryRepository
    ) {
        this.internshipRepository = internshipRepository;
        this.eventRepository = eventRepository;
        this.projectRepository = projectRepository;
        this.registrationRepository = registrationRepository;
        this.paymentDetailRepository = paymentDetailRepository;
        this.contactInquiryRepository = contactInquiryRepository;
    }

    @Override
    public DashboardSummaryResponse getDashboardSummary() {
        DashboardSummaryResponse response = new DashboardSummaryResponse();
        response.setTotalInternships(internshipRepository.count());
        response.setTotalEvents(eventRepository.count());
        response.setTotalProjects(projectRepository.count());
        response.setTotalRegistrations(registrationRepository.count());
        response.setApprovedRegistrations(
                registrationRepository.countBySelectionStatus(SelectionStatus.APPROVED)
        );
        response.setPendingPayments(
                registrationRepository.countByPaymentStatus(PaymentStatus.SUBMITTED)
        );
        response.setVerifiedPayments(
                registrationRepository.countByPaymentStatus(PaymentStatus.VERIFIED)
        );
        response.setTotalContactInquiries(contactInquiryRepository.count());
        return response;
    }
}