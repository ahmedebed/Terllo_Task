package com.example.Trello_style.DTO.respnse;

import com.example.Trello_style.Entity.User;
import lombok.Builder;

@Builder
public record UserResponse (
        long id,
        String name,
        String email
){
    public static  UserResponse mapToUser(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
