package com.example.Trello_style.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GlobalService {
    private final JwtService jwtService;
    public Long extractUserIdFromToken(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        return jwtService.extractUserId(token);
    }
    private String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
