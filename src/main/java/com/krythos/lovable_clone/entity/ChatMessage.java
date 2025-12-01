package com.krythos.lovable_clone.entity;

import java.time.Instant;

import com.krythos.lovable_clone.enums.MessageRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

    private long id;

    private ChatSession chatSession;

    private String content;

    private MessageRole messageRole;

    private String toolCalls;

    private Integer tokenUser;

    private Instant createdAt;




}
