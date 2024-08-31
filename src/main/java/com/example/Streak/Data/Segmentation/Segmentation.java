package com.example.Streak.Data.Segmentation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("segmentation")
public record Segmentation (
        @Id
        String msisdn,
        Long segmentId
)
{}
