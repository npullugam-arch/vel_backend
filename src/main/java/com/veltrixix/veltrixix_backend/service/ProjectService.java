package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.project.ProjectRequest;
import com.veltrixix.veltrixix_backend.dto.project.ProjectResponse;
import com.veltrixix.veltrixix_backend.dto.project.ProjectStatusUpdateRequest;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    ProjectResponse getProjectById(Long id);

    List<ProjectResponse> getAllProjects();

    List<ProjectResponse> getProjectsByCategory(String category);

    void updateProjectStatus(Long id, ProjectStatusUpdateRequest request);

    void deleteProject(Long id);
}