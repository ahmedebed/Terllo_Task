package com.example.Trello_style.DTO.respnse;

import com.example.Trello_style.Entity.Task;
import lombok.Builder;

@Builder
public record TaskResponse(
        long id,
        String title,
        String description,
        String status
) {
    public static TaskResponse mapToTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().toString())
                .build();
    }
}
