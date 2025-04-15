package com.example.Trello_style.DTO.respnse;

import com.example.Trello_style.Entity.Project;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProjectResponse(
        long id,
        String name,
        String description,
        List<TaskResponse> tasks
) {
    public static ProjectResponse mapToBasicProjectResponse(Project project){
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .build();
    }
    public static ProjectResponse mapToDetailesProjectResponse(Project project){
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .tasks(project.getTasks().stream()
                        .map(TaskResponse::mapToTaskResponse)
                        .toList())
                .build();
    }

}
