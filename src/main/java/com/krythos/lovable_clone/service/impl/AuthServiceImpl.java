package com.krythos.lovable_clone.service.impl;

import com.krythos.lovable_clone.dto.auth.AuthResponse;
import com.krythos.lovable_clone.dto.auth.LoginRequest;
import com.krythos.lovable_clone.dto.auth.SignupRequest;
import com.krythos.lovable_clone.entity.User;
import com.krythos.lovable_clone.exception.BadRequestException;
import com.krythos.lovable_clone.mapper.UserMapper;
import com.krythos.lovable_clone.repository.UserRepository;
import com.krythos.lovable_clone.security.AuthUtil;
import com.krythos.lovable_clone.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        userRepository.findByUsername(signupRequest.username()).ifPresent(user -> {
            throw new BadRequestException("user already exists with username " + signupRequest.username());
        });

        User user = userMapper.toEntity(signupRequest);
        user.setPassword(passwordEncoder.encode(signupRequest.password()));
        userRepository.save(user);

        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }
}
