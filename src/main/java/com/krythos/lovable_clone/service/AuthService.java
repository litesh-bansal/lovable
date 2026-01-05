package com.krythos.lovable_clone.service;


import com.krythos.lovable_clone.dto.auth.AuthResponse;
import com.krythos.lovable_clone.dto.auth.LoginRequest;
import com.krythos.lovable_clone.dto.auth.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest signupRequest);

    AuthResponse login(LoginRequest request);
}

