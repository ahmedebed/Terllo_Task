package com.example.Trello_style.DTO.request;

import com.example.Trello_style.Validation.OnCreate;
import com.example.Trello_style.Validation.OnUpdate;
import com.example.Trello_style.Validation.OptionalNotBlank;
import com.example.Trello_style.Validation.UniqueEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "Email must not be blank",groups = OnCreate.class)
        @OptionalNotBlank(groups = OnUpdate.class)
        @UniqueEmail(message = "Email is already taken please Enter anther Email")
        String email,
        @NotBlank(message = "name must not be blank")
        @OptionalNotBlank(groups = OnUpdate.class)
        String name,
        @NotBlank(message = "Password must not be blank")
        @OptionalNotBlank(groups = OnUpdate.class)
        String password
) {
}
