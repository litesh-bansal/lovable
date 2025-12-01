package com.krythos.lovable_clone.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Project {

    private long id;

    private String name;

    private User owner;

    private Boolean isPrivate;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

}
