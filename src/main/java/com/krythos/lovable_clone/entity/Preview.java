package com.krythos.lovable_clone.entity;

import com.krythos.lovable_clone.enums.PreviewStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Preview {

    private long id;

    private Project project;

    private String namespace;

    private String podName;

    private String previewUrl;

    private PreviewStatus status;

    private Instant startedAt;

    private Instant terminatedAt;

    private Instant createdAt;
}
