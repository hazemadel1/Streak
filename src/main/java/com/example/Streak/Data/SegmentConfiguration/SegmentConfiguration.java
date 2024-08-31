package com.example.Streak.Data.SegmentConfiguration;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table ("segment_configuration")
public record SegmentConfiguration(
        @Id  Long id,
        Long limitation
    )
    {}
