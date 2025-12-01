package com.krythos.lovable_clone.service;

import com.krythos.lovable_clone.dto.auth.UserProfileResponse;

public interface UserService {

    UserProfileResponse getProfile(long userId);
}
