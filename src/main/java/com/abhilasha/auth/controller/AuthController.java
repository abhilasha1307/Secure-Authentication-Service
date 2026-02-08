package com.abhilasha.auth.controller;

import com.abhilasha.auth.controller.dto.*;
import com.abhilasha.auth.domain.RefreshToken;
import com.abhilasha.auth.domain.User;
import com.abhilasha.auth.security.JwtUtil;
import com.abhilasha.auth.service.AuthService;
import com.abhilasha.auth.service.RefreshTokenService;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        User user = authService.register(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterResponse(user.getId(), user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(
                authService.login(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }


    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(
            @RequestBody RefreshRequest request
    ) {
        User user = refreshTokenService
                .validateAndGetUser(request.getRefreshToken());

        String newAccessToken = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(
                new RefreshResponse(newAccessToken)
        );
    }


}
