package com.example.Trello_style.DTO.request;

import com.example.Trello_style.Validation.OnCreate;
import com.example.Trello_style.Validation.OnUpdate;
import com.example.Trello_style.Validation.OptionalNotBlank;
import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(
        @NotBlank(message = "name must not be blank",
                        groups = {OnCreate.class})
        @OptionalNotBlank(groups = {OnUpdate.class})
        String name,
        @NotBlank(message = "description must not be blank",
                        groups = {OnCreate.class})
        @OptionalNotBlank(groups = {OnUpdate.class})
        String description
) {
}
