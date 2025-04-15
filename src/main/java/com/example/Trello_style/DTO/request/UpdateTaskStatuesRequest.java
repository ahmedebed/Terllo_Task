package com.example.Trello_style.DTO.request;

import com.example.Trello_style.Enum.Status;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatuesRequest(
        @NotNull(message = "Status must not be null")
        String status
) {
}
