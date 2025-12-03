package com.krythos.lovable_clone.dto.project;

import java.time.Instant;

public record ProjectSummaryResponse(
        long id,
        String name,
        Instant createdAt
) {
}
