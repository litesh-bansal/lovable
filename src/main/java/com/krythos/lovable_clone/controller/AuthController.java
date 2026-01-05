package com.krythos.lovable_clone.controller;

import com.krythos.lovable_clone.dto.auth.AuthResponse;
import com.krythos.lovable_clone.dto.auth.LoginRequest;
import com.krythos.lovable_clone.dto.auth.SignupRequest;
import com.krythos.lovable_clone.dto.auth.UserProfileResponse;
import com.krythos.lovable_clone.service.AuthService;
import com.krythos.lovable_clone.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        long userId = 1L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }
}
