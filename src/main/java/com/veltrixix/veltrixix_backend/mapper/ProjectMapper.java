package com.veltrixix.veltrixix_backend.mapper;

import com.veltrixix.veltrixix_backend.dto.project.ProjectResponse;
import com.veltrixix.veltrixix_backend.entity.Project;

public final class ProjectMapper {

    private ProjectMapper() {
    }

    public static ProjectResponse toResponse(Project project) {
        if (project == null) {
            return null;
        }

        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setTitle(project.getTitle());
        response.setDescription(project.getDescription());
        response.setMentorName(project.getMentorName());
        response.setTeamInfo(project.getTeamInfo());
        response.setCategory(project.getCategory() != null ? project.getCategory().name() : null);
        response.setStatusText(project.getStatusText());
        response.setCollaborationOpen(project.getCollaborationOpen());
        response.setCreatedAt(project.getCreatedAt());
        response.setUpdatedAt(project.getUpdatedAt());
        return response;
    }
}