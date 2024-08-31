package com.example.Streak.Service;

import com.example.Streak.Data.Segmentation.Segmentation;
import com.example.Streak.Data.Segmentation.SegmentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentationService {
    @Autowired
    private SegmentationRepository segmentationRepository;

    public Long getSegmentIdByMsisdn(String msisdn) {
        Segmentation segmentation = segmentationRepository.findByMsisdn(msisdn);
        if (segmentation == null) {
            throw new IllegalArgumentException("Msisdn not in white list");
        }
        return segmentation.segmentId();
    }
}
