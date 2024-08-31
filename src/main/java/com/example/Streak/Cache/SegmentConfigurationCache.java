package com.example.Streak.Cache;

import com.example.Streak.Data.SegmentConfiguration.SegmentConfiguration;
import com.example.Streak.Data.SegmentConfiguration.SegmentConfigurationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SegmentConfigurationCache {
    private final SegmentConfigurationRepository repository;
    private Map<Long, SegmentConfiguration> cache = new HashMap<>();

    public SegmentConfigurationCache(SegmentConfigurationRepository repository) {
        this.repository = repository;
    }

    @Scheduled(initialDelay = 900_000, fixedDelay = 3_000)
    public void refreshSegmentCache() {
        Map<Long, SegmentConfiguration> temp = new HashMap<>();
        repository.findAll().forEach(segmentConfiguration -> temp.put(segmentConfiguration.id(), segmentConfiguration));
        cache = temp;
    }

    public SegmentConfiguration getSegment(Long limitation) {
        if (cache.isEmpty()) {
            refreshSegmentCache();
        }
        return cache.get(limitation);
    }
}
