package com.example.Streak.Data;

import org.springframework.data.annotation.Id;

public record Reward (
     @Id Long id,
     Long data,
     int streakDays

)
    {}
