package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.project.ProjectRequest;
import com.veltrixix.veltrixix_backend.dto.project.ProjectResponse;
import com.veltrixix.veltrixix_backend.dto.project.ProjectStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.entity.Project;
import com.veltrixix.veltrixix_backend.enums.ProjectCategory;
import com.veltrixix.veltrixix_backend.exception.BadRequestException;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.mapper.ProjectMapper;
import com.veltrixix.veltrixix_backend.repository.ProjectRepository;
import com.veltrixix.veltrixix_backend.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Project project = new Project();
        mapRequestToEntity(request, project);
        return ProjectMapper.toResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        mapRequestToEntity(request, project);
        return ProjectMapper.toResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return ProjectMapper.toResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProjectResponse> getProjectsByCategory(String category) {
        ProjectCategory projectCategory = parseProjectCategory(category);
        return projectRepository.findByCategory(projectCategory)
                .stream()
                .map(ProjectMapper::toResponse)
                .toList();
    }

    @Override
    public void updateProjectStatus(Long id, ProjectStatusUpdateRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        project.setStatusText(request.getStatusText());
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        projectRepository.delete(project);
    }

    private void mapRequestToEntity(ProjectRequest request, Project project) {
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setMentorName(request.getMentorName());
        project.setTeamInfo(request.getTeamInfo());
        project.setCategory(parseProjectCategory(request.getCategory()));
        project.setStatusText(request.getStatusText());
        project.setCollaborationOpen(
                request.getCollaborationOpen() != null ? request.getCollaborationOpen() : false
        );
    }

    private ProjectCategory parseProjectCategory(String category) {
        try {
            return ProjectCategory.valueOf(category.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid project category value");
        }
    }
}