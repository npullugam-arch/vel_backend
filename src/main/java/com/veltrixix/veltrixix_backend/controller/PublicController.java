package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.event.EventResponse;
import com.veltrixix.veltrixix_backend.dto.internship.InternshipResponse;
import com.veltrixix.veltrixix_backend.dto.project.ProjectResponse;
import com.veltrixix.veltrixix_backend.service.EventService;
import com.veltrixix.veltrixix_backend.service.InternshipService;
import com.veltrixix.veltrixix_backend.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final InternshipService internshipService;
    private final EventService eventService;
    private final ProjectService projectService;

    public PublicController(
            InternshipService internshipService,
            EventService eventService,
            ProjectService projectService
    ) {
        this.internshipService = internshipService;
        this.eventService = eventService;
        this.projectService = projectService;
    }

    @GetMapping("/internships")
    public ApiResponse<List<InternshipResponse>> getPublicInternships() {
        return ApiResponse.success(internshipService.getAllInternships());
    }

    @GetMapping("/events")
    public ApiResponse<List<EventResponse>> getPublicEvents() {
        return ApiResponse.success(eventService.getAllEvents());
    }

    @GetMapping("/projects")
    public ApiResponse<List<ProjectResponse>> getPublicProjects() {
        return ApiResponse.success(projectService.getAllProjects());
    }
}