package com.example.Trello_style.Validation;

import com.example.Trello_style.Repo.UserRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepo userRepo;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !userRepo.findByEmail(email).isPresent();
    }
}
