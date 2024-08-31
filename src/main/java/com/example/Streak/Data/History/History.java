package com.example.Streak.Data.History;

import org.springframework.data.annotation.Id;

public record History (
        @Id Long id,
        String msisdn,
        int streakLength,
        Long rewardGranted
     )
{}