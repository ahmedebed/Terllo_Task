package com.example.Trello_style.Service;

import com.example.Trello_style.DTO.request.RegisterRequest;
import com.example.Trello_style.DTO.respnse.UserResponse;
import com.example.Trello_style.Entity.User;
import com.example.Trello_style.Excptions.ResourceNotFoundException;
import com.example.Trello_style.Repo.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public UserResponse register(@Valid RegisterRequest request) {
        User user = User.builder()
                .email(request.email())
                .name(request.name())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepo.save(user);

        return UserResponse.mapToUser(user);
    }
    public User getUserByEmail(String email){
       return userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("user not found"));
    }
}
