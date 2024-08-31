package com.example.Streak.Data.DailyUsage;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record DailyUsage (
     @Id Long id,
     String msisdn,
     LocalDate date,
     Long dataUsed

)
{}
