package com.krythos.lovable_clone.dto.member;

import com.krythos.lovable_clone.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {

}
