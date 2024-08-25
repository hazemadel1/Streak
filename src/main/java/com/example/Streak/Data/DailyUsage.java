package com.example.Streak.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

public record DailyUsage (
     @Id Long id,
     String msisdn,
     LocalDate date,
     Long dataUsed

)
{}
