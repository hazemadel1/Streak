package com.example.Streak.Service;

import com.example.Streak.Cache.SegmentConfigurationCache;
import com.example.Streak.Data.SegmentConfiguration.SegmentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentConfigurationService {
    private final SegmentConfigurationCache segmentConfigurationCache;

    @Autowired
    public SegmentConfigurationService(SegmentConfigurationCache segmentConfigurationCache) {
        this.segmentConfigurationCache = segmentConfigurationCache;
    }

    public SegmentConfiguration getSegmentLimitation(Long limitation){
        return segmentConfigurationCache.getSegment(limitation);
    }

}
