package com.veltrixix.veltrixix_backend.entity;

import com.veltrixix.veltrixix_backend.enums.ProjectCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "mentor_name", length = 100)
    private String mentorName;

    @Column(name = "team_info", columnDefinition = "TEXT")
    private String teamInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 50)
    private ProjectCategory category;

    @Column(name = "status_text", length = 100)
    private String statusText;

    @Column(name = "collaboration_open", nullable = false)
    private Boolean collaborationOpen = false;

    public Project() {
    }

    public Long getId() {
        return id;
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

    public ProjectCategory getCategory() {
        return category;
    }

    public void setCategory(ProjectCategory category) {
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