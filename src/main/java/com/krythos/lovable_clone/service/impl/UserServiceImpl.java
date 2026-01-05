package com.krythos.lovable_clone.service.impl;

import com.krythos.lovable_clone.dto.auth.UserProfileResponse;
import com.krythos.lovable_clone.exception.ResourceNotFoundException;
import com.krythos.lovable_clone.repository.UserRepository;
import com.krythos.lovable_clone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserProfileResponse getProfile(long userId) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("username", username));
    }
}
