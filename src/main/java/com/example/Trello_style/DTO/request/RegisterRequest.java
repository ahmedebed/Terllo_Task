package com.example.Trello_style.DTO.request;

import com.example.Trello_style.Validation.UniqueEmail;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "Email must not be blank")
        String email,
        @NotBlank(message = "name must not be blank")
        String name,
        @UniqueEmail(message = "Email is already taken please Enter anther Email")
        @NotBlank(message = "Password must not be blank")
        String password
) {
}
