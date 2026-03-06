package com.globits.demo.dto;

import com.globits.demo.model.Project;

public class ProjectCreateDTO {
    private Integer id;
    private String code;
    private String name;
    private String description;

    public ProjectCreateDTO() {}
    public ProjectCreateDTO(Project project) {
        if (project != null) {
            this.code = project.getCode();
            this.name = project.getName();
            this.description = project.getDescription();
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
