package com.krythos.lovable_clone.dto.auth;

public record UserProfileResponse(
        long id,
        String username,
        String name
) {
}
