package com.example.Streak.Data;

import org.springframework.data.annotation.Id;

public record History (
        @Id Long id,
        String msisdn,
        int streakLength,
        Long rewardGranted
     )
{}