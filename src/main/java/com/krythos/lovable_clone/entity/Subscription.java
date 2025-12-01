package com.krythos.lovable_clone.entity;

import com.krythos.lovable_clone.enums.SubscriptionStatus;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Subscription {

    private long id;

    @ManyToOne
    private User user;

    private Plan plan;

    private SubscriptionStatus subscriptionStatus;

    private String stripeCustomerId;

    private String stripeSubscriptionId;

    private Integer currentPeriodStart;

    private Instant currentPeriodEnd;

    private Boolean cancelAtPeriodEnd;

    private Instant createdAt;

    private Instant updatedAt;


}
