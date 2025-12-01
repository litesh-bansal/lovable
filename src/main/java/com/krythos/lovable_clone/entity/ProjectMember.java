package com.krythos.lovable_clone.entity;

import com.krythos.lovable_clone.enums.ProjectRole;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProjectMember {

    private ProjectMemberId id;

    private Project project;

    private User user;

    private ProjectRole role;

    private Instant invitedAt;

    private Instant acceptedAt;

}
