package com.krythos.lovable_clone.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UsageLog {

    private long id;

    private User user;

    private Project project;

    private String action;

    private Integer tokenUsed;

    private Integer durationMs;

    private String metaData;

    private Instant createdAt;

}
