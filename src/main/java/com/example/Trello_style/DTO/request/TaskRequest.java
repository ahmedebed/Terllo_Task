package com.example.Trello_style.DTO.request;

import com.example.Trello_style.Validation.OnCreate;
import com.example.Trello_style.Validation.OnUpdate;
import com.example.Trello_style.Validation.OptionalNotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TaskRequest(
        @NotBlank(message = "Title must not be blank",groups = {OnCreate.class})
        @OptionalNotBlank(groups = {OnUpdate.class})
        String title,
        @NotBlank(message = "Description must not be blank",groups = {OnCreate.class})
        @OptionalNotBlank(groups = {OnUpdate.class})
        String description
) {
}
