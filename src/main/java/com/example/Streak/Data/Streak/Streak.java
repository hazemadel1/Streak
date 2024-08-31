package com.example.Streak.Data.Streak;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("streaks")
public record Streak (
     @Id Long id,
     String msisdn,
     int currentStreak,
     LocalDate lastStreakDate

)
    {}
