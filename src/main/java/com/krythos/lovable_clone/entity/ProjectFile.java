package com.krythos.lovable_clone.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProjectFile {

    private long id;

    private Project project;

    private String path;

    private String minObjectKey;

    private Instant createdAt;

    private Instant updatedAt;

    private User createdBy;

    private User updatedBy;
}
