package com.krythos.lovable_clone.service;

import com.krythos.lovable_clone.dto.usage.PlanLimitResponse;
import com.krythos.lovable_clone.dto.usage.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
