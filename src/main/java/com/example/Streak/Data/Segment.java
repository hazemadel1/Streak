package com.example.Streak.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table ("segment")
public record Segment (
        @Id  Long id,
        Long limitation

    )
    {}
