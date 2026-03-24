package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.dto.project.ProjectRequest;
import com.veltrixix.veltrixix_backend.dto.project.ProjectResponse;
import com.veltrixix.veltrixix_backend.dto.project.ProjectStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ApiResponse<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request) {
        return ApiResponse.success("Project created successfully", projectService.createProject(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request
    ) {
        return ApiResponse.success("Project updated successfully", projectService.updateProject(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProjectResponse> getProjectById(@PathVariable Long id) {
        return ApiResponse.success(projectService.getProjectById(id));
    }

    @GetMapping
    public ApiResponse<List<ProjectResponse>> getAllProjects() {
        return ApiResponse.success(projectService.getAllProjects());
    }

    @GetMapping("/category/{category}")
    public ApiResponse<List<ProjectResponse>> getProjectsByCategory(@PathVariable String category) {
        return ApiResponse.success(projectService.getProjectsByCategory(category));
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<MessageResponse> updateProjectStatus(
            @PathVariable Long id,
            @Valid @RequestBody ProjectStatusUpdateRequest request
    ) {
        projectService.updateProjectStatus(id, request);
        return ApiResponse.success("Project status updated successfully", MessageResponse.of("Project status updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MessageResponse> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ApiResponse.success("Project deleted successfully", MessageResponse.of("Project deleted successfully"));
    }
}