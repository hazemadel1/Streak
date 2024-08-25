package com.example.Streak.Data;

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
