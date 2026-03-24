package com.veltrixix.veltrixix_backend.dto.project;

import jakarta.validation.constraints.NotBlank;

public class ProjectRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String mentorName;
    private String teamInfo;
    private String category;
    private String statusText;
    private Boolean collaborationOpen;

    public ProjectRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(String teamInfo) {
        this.teamInfo = teamInfo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Boolean getCollaborationOpen() {
        return collaborationOpen;
    }

    public void setCollaborationOpen(Boolean collaborationOpen) {
        this.collaborationOpen = collaborationOpen;
    }
}