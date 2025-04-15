package com.example.Trello_style.Controller;

import com.example.Trello_style.DTO.request.LoginRequest;
import com.example.Trello_style.DTO.request.RegisterRequest;
import com.example.Trello_style.DTO.respnse.UserResponse;
import com.example.Trello_style.Entity.User;
import com.example.Trello_style.Repo.UserRepo;
import com.example.Trello_style.Service.AuthService;
import com.example.Trello_style.Service.JwtService;
import com.example.Trello_style.Validation.OnCreate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Validated(OnCreate.class) @RequestBody RegisterRequest request){
        UserResponse newUser = authService.register(request);
        return ResponseEntity.ok(newUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );
        UserDetails userDetails = authService.getUserByEmail(loginRequest.email());
        Optional<User> user =userRepo.findByEmail(loginRequest.email());
        String token = jwtService.generateToken(userDetails,user.get().getId());
        return ResponseEntity.ok(token);
    }
}
