package com.krythos.lovable_clone.dto.member;

import com.krythos.lovable_clone.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role
) {
}
