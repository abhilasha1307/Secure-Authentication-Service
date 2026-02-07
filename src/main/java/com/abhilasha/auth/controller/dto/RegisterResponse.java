package com.abhilasha.auth.controller.dto;
import lombok.Getter;
import java.util.UUID;

@Getter
public class RegisterResponse {

    private UUID userId;
    private String email;

    public RegisterResponse(UUID userId, String email) {
        this.userId = userId;
        this.email = email;
    }

}