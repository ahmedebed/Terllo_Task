package com.example.Trello_style.DTO.respnse;

import com.example.Trello_style.Entity.Project;
import lombok.Builder;

@Builder
public record ProjectResponse(
        long id,
        String name,
        String description
) {
    public static ProjectResponse mapToBasicProjectResponse(Project project){
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .build();
    }

}
