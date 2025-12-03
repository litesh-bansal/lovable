package com.krythos.lovable_clone.dto.usage;

public record UsageTodayResponse(
        Integer tokenUsed,
        Integer tokenLimit,
        Integer previewRunning,
        Integer previewLimit,
        Integer MaxTokenPerDay
) {
}
