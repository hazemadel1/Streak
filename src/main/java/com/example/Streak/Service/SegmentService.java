package com.example.Streak.Service;

import com.example.Streak.Data.Segment;
import com.example.Streak.Data.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentService {
    @Autowired
    private SegmentRepository segmentRepository;


}
