package com.krythos.lovable_clone.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plan {

    private long id;

    private String stripePriceId;

    private Integer maxProjects;

    private Integer MaxTokenPerDay;

    private Integer maxPreviews;

    private Boolean unlimitedAi; //unlimited access to LLM, ignore maxTokensPerDay

    private Boolean active;
}
